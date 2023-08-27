package com.proyectospring.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.service.CustomUserDetails;
import com.proyectospring.app.models.service.IUsuarioService;

@Controller
public class LoginController {
	
	
	
	
	/**
	 * método para el login que devolverá la vista del login
	 */
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout,@RequestParam(value = "email", required = false) String email,Model modelo, Principal principal, RedirectAttributes flash) { // Principal es el usuario que puede estar ya logeado
		
		
		
		if (principal != null) {
			
			CustomUserDetails userDetails = (CustomUserDetails) ((Authentication) principal).getPrincipal();
	        if (userDetails.getUserEnabled() == 0) {
	            return "redirect:/salir";
	        }
			
		}
		
		
		
		if (error != null) {
			
			modelo.addAttribute("error", "Error en el Login: Correo Electrónico o Contraseña incorrectos"); 
			return "redirect:/login";
		}
		
		if (logout != null) {
			
			flash.addFlashAttribute("info", "Ha cerrado sesion con éxito");
			modelo.addAttribute("success", "Ha cerrado sesion con éxito");
		}
		
		
		return"login";
	}

}
