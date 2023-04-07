package com.alkimi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Value("${user}")
	private String  userName;

	
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello Mr." + userName;
	}

}
