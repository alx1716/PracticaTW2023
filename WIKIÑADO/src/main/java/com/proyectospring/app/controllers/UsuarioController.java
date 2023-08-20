package com.proyectospring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioArticulo;
import com.proyectospring.app.models.entity.UsuarioWiki;
import com.proyectospring.app.models.entity.Wiki;
import com.proyectospring.app.models.service.CustomUserDetails;
import com.proyectospring.app.models.service.IArticuloService;
import com.proyectospring.app.models.service.IUsuarioArticuloService;
import com.proyectospring.app.models.service.IUsuarioService;
import com.proyectospring.app.models.service.IUsuarioWikiService;
import com.proyectospring.app.models.service.IWikiService;
/**
 * Esta clase va a ser el controlador de los usuarios para las vistas de perfil
 * y más cosas que vayamos necesitando
 * 
 */
@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IWikiService wikiService;
	
	
	@Autowired
	private IUsuarioWikiService usuarioWikiService;
	
	@Autowired
	private IUsuarioArticuloService usuarioArticuloService;
	
	@Autowired
	private IArticuloService articuloService;
	
	
	@GetMapping("/perfil")
	public String perfil(Authentication authentication, Model modelo) {
		
		//recupero la información del usuario autenticado y lo recupero de la BBDD mediante el ID
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	    Long userId = userDetails.getUserId();
	    
	   
	    
	    modelo.addAttribute("usuario", usuarioService.findOne(userId)); // este es el usuario para poder acceder a TODA la información de dicho usuario
		
		return "perfil";
	}
	
	
	/**
	 * Método para asignar una Wiki al usuario //OJO este método lo cambiaré al controller de la entidad intermedia que he creado!! ya que ahí voy a manejar el borrado de los registros también
	 * @param wikiId ID de la wiki que queremos asignar
	 * @param flash para dar un mensaje de éxito en caso tal
	 * @return
	 */
	@PostMapping("/asignarWiki/{wikiId}")
	public String asignarWiki(@PathVariable Long wikiId, @RequestParam(required = false) Long userId, RedirectAttributes flash,  SessionStatus status) {
		
		//verificar que se ha escogido un usuario para asignar
		if (userId == null) {
			
			flash.addFlashAttribute("error", "No está escogiendo a un cuñado para asignar");
			return "redirect:/ver/"+wikiId;
		}
		
		
	    // Realizar la asignación de la wiki al usuario aquí
		Wiki wiki= wikiService.findOne(wikiId);
		Usuario usuario = usuarioService.findOne(userId);
		UsuarioWiki wikiAsignada = new UsuarioWiki();
		
		try {
			
			//Tengo que validar que el usuario no tenga ya esa wiki asignada sino me da error
			if (usuarioWikiService.findByUsuarioAndWiki(usuario, wiki) != null) {
				flash.addFlashAttribute("error", "El usuario ya tiene esa wiki asignada!!");
			    return "redirect:/ver/"+wikiId;
			}
			wikiAsignada.setUsuario(usuario);
			wikiAsignada.setWiki(wiki);
			usuario.getUsuarioWikis().add(wikiAsignada);
			wiki.getUsuarioWikis().add(wikiAsignada);
			
			usuarioWikiService.save(wikiAsignada);
			
			status.setComplete(); // se debe cerrar la sesion despues de guardar, esto elimina el objeto cliente
			// de la session y termina el proceso.
			
			// ahora tengo que validar que el usuario es un SUPERVISOR o COORDINADOR
			
		    flash.addFlashAttribute("success", "Wiki asignada con éxito al usuario "+usuario.getUsername());
			
		} catch (Exception e) {
			/*flash.addFlashAttribute("error", "Se me está duplicando la cosa");
			return "redirect:/listar";*/
			e.printStackTrace();
		}
	    return "redirect:/ver/"+wikiId;
	}
	
	
	
	/**
	 * Método para asignar un Artículo al usuario //OJO este método lo cambiaré al controller de la entidad intermedia que he creado!! ya que ahí voy a manejar el borrado de los registros también
	 * @param articuloId ID del artículo que queremos asignar
	 * @param flash para dar un mensaje de éxito en caso tal
	 * @return
	 */
	@PostMapping("/asignarArticulo/{articuloId}")
	public String asignarArticulo(@PathVariable Long articuloId, @RequestParam(required = false) Long userId, RedirectAttributes flash,  SessionStatus status) {
		
		
		
		//verificar que se ha escogido un usuario para asignar
				if (userId == null) {
					
					flash.addFlashAttribute("error", "No está escogiendo a un cuñado para asignar");
					return "redirect:/articulo/ver/"+articuloId;
				}
		
	    // Realizar la asignación de la wiki al usuario aquí
		Articulo articulo= articuloService.findOne(articuloId);
		Usuario usuario = usuarioService.findOne(userId);
		UsuarioArticulo articuloAsignado = new UsuarioArticulo();
		
		try {
			
			//Tengo que validar que el usuario no tenga ya esa wiki asignada sino me da error
			if (usuarioArticuloService.findByUsuarioAndArticulo(usuario, articulo) != null) {
				flash.addFlashAttribute("error", "El usuario ya tiene ese artículo asignado!!");
			    return "redirect:/articulo/ver/"+articuloId;
			}
			articuloAsignado.setUsuario(usuario);
			articuloAsignado.setArticulo(articulo);
			usuario.getUsuarioArticulos().add(articuloAsignado);
			articulo.getUsuarioArticulos().add(articuloAsignado);
			
			usuarioArticuloService.save(articuloAsignado);
			
			status.setComplete(); // se debe cerrar la sesion despues de guardar, esto elimina el objeto cliente
			// de la session y termina el proceso.
			
			// ahora tengo que validar que el usuario es un SUPERVISOR o COORDINADOR
			
		    flash.addFlashAttribute("success", "Artículo asignado con éxito al usuario "+usuario.getUsername());
			
		} catch (Exception e) {
			/*flash.addFlashAttribute("error", "Se me está duplicando la cosa");
			return "redirect:/listar";*/
			e.printStackTrace();
		}
	    return "redirect:/articulo/ver/"+articuloId;
	}

	

}
