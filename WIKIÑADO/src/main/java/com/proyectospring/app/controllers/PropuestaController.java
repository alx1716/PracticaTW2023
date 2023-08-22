package com.proyectospring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyectospring.app.models.entity.UsuarioArticulo;
import com.proyectospring.app.models.service.IUsuarioArticuloService;

@Controller
public class PropuestaController {
	
	@Autowired
	IUsuarioArticuloService usuarioArticuloService;
	
	@GetMapping("/revisar/{articuloAsignado_id}")
	public String revisarPropuesta( @PathVariable Long articuloAsignado_id,Model modelo) {
		
		UsuarioArticulo articuloAsignado = usuarioArticuloService.findOneById(articuloAsignado_id);
		
		modelo.addAttribute("articulo",	articuloAsignado.getArticulo()); // paso el artículo que tiene que ser revisado
		modelo.addAttribute("lista_propuestas", articuloAsignado.getArticulo().getPropuestas()); // paso la lista de propuestas para ese artículo
		
		return "revisarPropuesta";
	}

}
