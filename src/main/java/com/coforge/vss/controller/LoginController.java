package com.coforge.vss.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("https://abhishekcs16.github.io")
@RestController
//@RequestMapping(value="/login")
public class LoginController {
	
	@GetMapping(value = "/")
	public String login() {
		return "login success";
	}

}

