package com.demo.mvc.controller;

import com.demo.mvc.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by adon on 2016/1/30 0030.
 */
@Controller
public class MainController extends BaseController {

	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}

//	@RequestMapping("/mvcweb/test.do")
//	public String test() {
//		return "/mvcweb/test";
//	}


	@RequestMapping("/mvcweb/test.do")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ModelAndView mv = new ModelAndView();

		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print("{\"msg\",\"caonima\"}");

		return null;
	}
}
