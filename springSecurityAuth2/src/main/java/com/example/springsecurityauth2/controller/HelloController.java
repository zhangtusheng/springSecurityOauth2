package com.example.springsecurityauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zts
 * @date 2023/3/19 01:04
 * @Description
 */
@RestController
public class HelloController {

	@GetMapping("/hi")
	public String hi(String name){
		return "hi , " + name;
	}


}
