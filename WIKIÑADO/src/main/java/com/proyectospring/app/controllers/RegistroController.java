package com.proyectospring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectospring.app.models.entity.Role;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.service.RegistroService;
/**
 * Controlador para el registro de nuevos usuarios
 */
@Controller
public class RegistroController {
	
	@Autowired
	RegistroService registroService;
	
	/**
	 * Muestra la vista del registro
	 * @return la vista del formulario de registro
	 */
	@GetMapping("/registro")
	public String signup(Model modelo) {
		
		Usuario usuarioNuevo = new Usuario();
		modelo.addAttribute("usuario", usuarioNuevo);
		modelo.addAttribute("titulo", "Registro de Usuarios/Cuñaos");
		
		return "registro";
	}
	
	
	/**
	 * método para guardar un nuevo usuario en la BBDD
	 * que anteriormente se ha rellenado en el foremulario de registro
	 * 
	 * Creo que esto debería estar en un UsuarioController
	 */
	@PostMapping("/registro")
	public String guardar(Usuario nuevoUsuario, RedirectAttributes flash) {
		
		//si el usuario existe en la BBDD se redirige al login
		if (registroService.comprobarUsuario(nuevoUsuario)) {
			
			flash.addFlashAttribute("error", "El usuario ya se encuentra en la BBDD!!");
			return "redirect:/listar";
		}
		
		
		
		//se guarda el usuario
		
		registroService.saveNuevoUsuario(nuevoUsuario);
		
		// se manda una mensaje de éxito si el usuario ha sido guardado
		flash.addFlashAttribute("success", "Te has registrado con éxito!!, inicia sesion para cambiar el mundo!!");
		
		return "redirect:/listar"; // listar deberia ser la lista de las wikis o el home
	}
	
	

}
