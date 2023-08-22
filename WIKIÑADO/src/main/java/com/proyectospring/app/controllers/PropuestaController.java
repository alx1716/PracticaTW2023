package com.proyectospring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;
import com.proyectospring.app.models.entity.UsuarioArticulo;
import com.proyectospring.app.models.service.IArticuloService;
import com.proyectospring.app.models.service.IPropuestaModificacionService;
import com.proyectospring.app.models.service.IUsuarioArticuloService;

@Controller
public class PropuestaController {
	
	@Autowired
	IUsuarioArticuloService usuarioArticuloService;
	
	@Autowired
	private IArticuloService articuloService;
	
	@Autowired
	private IPropuestaModificacionService propuestaModificacionService;
	
	@GetMapping("/revisar/{articuloAsignado_id}")
	public String revisarPropuesta( @PathVariable Long articuloAsignado_id,Model modelo) {
		
		UsuarioArticulo articuloAsignado = usuarioArticuloService.findOneById(articuloAsignado_id);
		
		modelo.addAttribute("articulo",	articuloAsignado.getArticulo()); // paso el artículo que tiene que ser revisado
		modelo.addAttribute("lista_propuestas", articuloAsignado.getArticulo().getPropuestas()); // paso la lista de propuestas para ese artículo
		
		return "articulo/revisarPropuesta";
	}
	
	@GetMapping("/aceptar/{propuestaId}")
	public String aceptarPropuesta( @PathVariable Long propuestaId,RedirectAttributes flash) {
		
		PropuestaModificacion propuesta = propuestaModificacionService.findOne(propuestaId);
		Articulo articuloActual =  articuloService.findOne(propuesta.getArticulo().getId());
		articuloActual.setContenido(propuesta.getPropuesta());
		
		/*TENGO QUE CAMBIARLE EL ESTADO A LA PROPUESTA DEPUES DE SER ACEPTADA O RECHAZADA FLATA IMPLEMENTAR ESO Y CONTROL DE ERRORES*/
		
		articuloService.save(articuloActual);
		flash.addFlashAttribute("success", "Se acepta la sabiduría de este cuñado!! ahora regodeate en la contemplación de su obra");
		return"redirect:/articulo/ver/"+articuloActual.getId();  // redirijo a la vista del artículo
	}
	
	
	
	@GetMapping("/rechazar/{propuestaId}")
	public String rechazarPropuesta( @PathVariable Long propuestaId,RedirectAttributes flash) {
		
		PropuestaModificacion propuesta = propuestaModificacionService.findOne(propuestaId);
		Articulo articuloActual =  articuloService.findOne(propuesta.getArticulo().getId());
		
		
		/*TENGO QUE CAMBIARLE EL ESTADO A LA PROPUESTA DEPUES DE SER ACEPTADA O RECHAZADA FLATA IMPLEMENTAR ESO Y CONTROL DE ERRORES*/
		propuesta.setEstado("RECHAZADA");
		articuloService.save(articuloActual);
		flash.addFlashAttribute("error", "Haz rechazado esta perla de sabiduría, pero será por algo....");
		return"redirect:/articulo/ver/"+articuloActual.getId();  // redirijo a la vista del artículo
	}

}
