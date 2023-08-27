package com.proyectospring.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LogOutController {
	
	
	/**
	 * Método que redirije a al error para obligar al usuario a deslogearse
	 * ya que no está activo.
	 * @param flash para los mensajes de error
	 * @return la vista del error de acceso denegado.
	 */
	@GetMapping("/salir")
    public String logout(RedirectAttributes flash) {
        // Puedes agregar un mensaje de aviso aquí si lo deseas
        flash.addFlashAttribute("info", "Tu usuario ha sido deshabilitado contacta con tu administrador.");
        return "redirect:/error_403";
    }

}
