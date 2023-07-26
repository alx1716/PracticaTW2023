package com.wikinado.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControlador {
	
	@RequestMapping("/login")
	public String formularioLogin() {
		return "login";
	}

}
