package com.wikinado.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginSuccess {

	@GetMapping("/loginSuccessful")	
	public String home() {
		return "loginSuccessful";
	}
}
