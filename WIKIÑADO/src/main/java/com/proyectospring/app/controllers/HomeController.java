package com.proyectospring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.service.CustomUserDetails;
import com.proyectospring.app.models.service.IUsuarioService;

@Controller
public class HomeController {
	
	@Autowired
	IUsuarioService usuarioService;
	
	 @GetMapping({"/home","/",""})
	    public String home() {
	        return "home";
	    }
	 
	 
	 /**
	  * Este método me sirve para comprobar si el usuario está desactivado y por lo tanto 
	  * no debe tener acceso a la aplicación o si por el contrario es un usuario válido.
	  * 
	  * @param modelo se pasa a la vista
	  * @param flash para los mensajes de información o error
	  * @return la vista que tiene que ver el usuario en cada caso al intentar logearse
	  */
	 @GetMapping({"/comprobar"})
	 public String comprobar(Model modelo, RedirectAttributes flash) {
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		    Usuario usuario = usuarioService.findOne(userDetails.getUserId());
		    
		    if (usuario.getEnabled() == 0) {
		    	
		    	modelo.addAttribute("error", "Error en el Login: Correo Electrónico o Contraseña incorrectos"); 
				return "redirect:/login";
				
			}
		 
		 return"redirect:/listar";
	 }

}
