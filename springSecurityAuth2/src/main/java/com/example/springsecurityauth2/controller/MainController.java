package com.example.springsecurityauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zts
 * @date 2023/3/19 01:05
 * @Description
 */
@Controller
public class MainController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute( "loginError"  , true);
		return "login";
	}


}
