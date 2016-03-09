package com.demo.mvc.controller;

import com.demo.bean.Member;
import com.demo.common.util.MessageUtil;
import com.demo.common.util.Util;
import com.demo.mvc.common.BaseController;
import com.demo.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by adon on 2016/3/3 0003.
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;


	@RequestMapping("/login/register.do")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=utf-8");

		String mobile = request.getParameter("mobile");
		String nickname = request.getParameter("nickname");
		String password = StringUtils.trimToEmpty(request.getParameter("psw"));

		String ip = request.getHeader("X-Forwarded-For");
		if(org.springframework.util.StringUtils.hasText(ip)){
			ip = ip.indexOf(",") > 0 ? ip.substring(0,ip.indexOf(",")):ip;
		}else{
			ip = request.getRemoteAddr();
		}

		Map result = new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		Locale locale = getLocale(request);

		if(password.length()<6 || password.length()>32){
			result.put("result", 1);
			result.put("msg", getMessage("Error_password", locale));
			mapper.writeValue(response.getWriter(), result);
		}

		try{
			Member member = loginService.register(mobile, nickname, Util.md5(password), null);
			result.put("result", 0);
			result.put("mobile", member.getMobile());
		} catch (IllegalArgumentException iae){
			log.warn("注册参数不正确", iae);
			result.put("result", 1);
			result.put("msg", MessageUtil.getServiceMessage(LoginService.class, iae.getMessage(), locale));
		} catch (Exception e){
			log.error(e);
			result.put("result", 2);
			result.put("msg", getMessage("Service_error", locale));
		}

		mapper.writeValue(response.getWriter(), result);

		return null;
	}

	@RequestMapping("/login/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=utf-8");
		String target = request.getParameter("loginMember");
		String password = StringUtils.trimToEmpty(request.getParameter("psw"));

		String ip = request.getHeader("X-Forwarded-For");
		if(org.springframework.util.StringUtils.hasText(ip)){
			ip = ip.indexOf(",") > 0 ? ip.substring(0,ip.indexOf(",")):ip;
		}else{
			ip = request.getRemoteAddr();
		}

		Member member = loginService.login(target, Util.md5(password), null, ip, LoginService.CLIENT_TYPE_WEB);

		Map result = new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		Locale locale = getLocale(request);

		if(member==null){
			result.put("result", 1);
			result.put("msg", getMessage("Error_password", locale));
			mapper.writeValue(response.getWriter(), result);
			return null;
		}

		result.put("result", 0);
		result.put("msg", getMessage("Success_login", locale));
		mapper.writeValue(response.getWriter(), result);

		return null;
	}

	@RequestMapping("/login/modifyPsw.do")
	public ModelAndView modifyPsw(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=utf-8");
		String mobile = StringUtils.trimToNull(request.getParameter("mobile"));
		String oldPsw = StringUtils.trimToNull(request.getParameter("oldPsw"));
		String newPsw = StringUtils.trimToNull(request.getParameter("newPsw"));

		Map result = new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		Locale locale = getLocale(request);

		if(null==mobile || null==oldPsw || null==newPsw){

			result.put("result", 1);
			if(null==mobile){
				result.put("msg", getMessage("Blank_mobile", locale));
			} else if(null==oldPsw){
				result.put("msg", getMessage("Blank_old_password", locale));
			} else {
				result.put("msg", getMessage("Blank_new_password", locale));
			}
			mapper.writeValue(response.getWriter(), result);
			return null;
		}

		try{
			loginService.modifyPsw(mobile, Util.md5(oldPsw), Util.md5(newPsw));
			result.put("result", 0);
			result.put("msg", getMessage("Success_modify_psw", locale));
		} catch (IllegalArgumentException iae){
			log.warn(iae);
			result.put("result", 1);
			result.put("msg", MessageUtil.getServiceMessage(LoginService.class, iae.getMessage(), locale));
		} catch (Exception e){
			log.error(e);
			result.put("result", 2);
			result.put("msg", getMessage("Service_error", locale));
		}
		mapper.writeValue(response.getWriter(), result);

		return null;
	}

}
