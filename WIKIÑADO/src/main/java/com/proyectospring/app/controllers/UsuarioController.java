package com.proyectospring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.service.CustomUserDetails;
import com.proyectospring.app.models.service.IUsuarioService;
/**
 * Esta clase va a ser el controlador de los usuarios para las vistas de perfil
 * y más cosas que vayamos necesitando
 * 
 */
@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@GetMapping("/perfil")
	public String perfil(Authentication authentication, Model modelo) {
		
		//recupero la información del usuario autenticado y lo recupero de la BBDD mediante el ID
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	    Long userId = userDetails.getUserId();
	    
	   
	    
	    modelo.addAttribute("usuario", usuarioService.findOne(userId)); // este es el usuario para poder acceder a TODA la información de dicho usuario
		
		return "perfil";
	}
	

}
