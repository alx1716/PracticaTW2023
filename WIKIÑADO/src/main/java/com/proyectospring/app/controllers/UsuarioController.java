package com.proyectospring.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Esta clase va a ser el controlador de los usuarios para las vistas de perfil
 * y más cosas que vayamos necesitando
 * 
 */
@Controller
public class UsuarioController {
	
	@GetMapping("/perfil")
	public String perfil(Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // con esto cojo el objeto autenticado
		
		modelo.addAttribute("usuario", auth);
		
		return "perfil";
	}
	

}
