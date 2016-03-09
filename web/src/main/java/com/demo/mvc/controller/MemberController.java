package com.demo.mvc.controller;

import com.demo.bean.Member;
import com.demo.common.util.MessageUtil;
import com.demo.common.util.Util;
import com.demo.mvc.common.BaseController;
import com.demo.service.LoginService;
import com.demo.service.MemberService;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by adon on 2016/3/3 0003.
 */
@Controller
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;


	@RequestMapping("/member/memberInfo.do")
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=utf-8");
//		Map result = new HashMap();
		ObjectMapper mapper = new ObjectMapper();

		List list = memberService.queryMember(1, 10);

		mapper.writeValue(response.getWriter(), list);

		return null;
	}


	@RequestMapping("/member/memberInfoPage.do")
	public String memberInfoPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List list = memberService.queryMember(1, 10);
		request.setAttribute("data", list);

		return "/member/member_info";
	}

}
