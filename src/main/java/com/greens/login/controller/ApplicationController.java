package com.greens.login.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greens.login.model.UserDetails;
import com.greens.login.service.ApplicationService;

@Controller
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;

	@RequestMapping(value = { "/", "/login" })
	public String loadLogin() {
		return "signIn";
	}

	@RequestMapping("/register")
	public String loadRegister() {
		return "signUp";
	}

	@PostMapping("/loginUser")
	public ModelAndView login(UserDetails userDetails) {
		Map<String, Object> usr = applicationService.login(userDetails);
		UserDetails usrDtls = (UserDetails) usr.get("usrData");
		boolean vldLogin = (boolean) usr.get("vldLogin");
		String pgName = vldLogin ? "welcome" : "signIn";
		String data = vldLogin ? usrDtls.getUserName() : "Invalid User Credentials";
		ModelAndView mv = new ModelAndView();
		mv.setViewName(pgName);
		mv.addObject("msg", data);
		return mv;
	}

	@PostMapping("/registerUser")
	public String register(UserDetails userDetails) {
		applicationService.register(userDetails);
		return "signIn";
	}

}
