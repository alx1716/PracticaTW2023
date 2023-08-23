package com.proyectospring.app.controllers;


import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectospring.app.models.entity.Wiki;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.service.CustomUserDetails;
import com.proyectospring.app.models.service.IPropuestaModificacionService;
import com.proyectospring.app.models.service.IUsuarioService;
import com.proyectospring.app.models.service.IWikiService;

//@Secured("ROLE_GESTOR") // se anota la clase entera ya que TODOS los métodos deben ser sólo accesibles a los Gestores.
@Controller
@RequestMapping("/articulo")
@SessionAttributes("articulo")   // para que se mantenga el articulo en la sesion hasta que se persista en la BBDD
public class ArticuloController {
	
	@Autowired
	private IWikiService wikiService;
	
	@Autowired
	private IPropuestaModificacionService propuestaService;
	
	@Autowired
	private IUsuarioService userService;
	
	
	/**
	 * método para ver el detalle de la factura de un cliente
	 */
	@GetMapping("/ver/{articuloId}")
	public String verDetalleArticulo(@PathVariable(value = "articuloId") Long articuloId, Authentication authentication,  Model modelo, RedirectAttributes flash) throws IOException { // la Exception es por el url del archivo que vamos a buscar
		
		//obtenemos el articulo por su id
		Articulo articulo = wikiService.findArticuloById(articuloId);
		
		//obtenemos la lista de los usuarios registrados para pasarla a la vista y poder asignar los artículos a los usuarios
		List<Usuario> usuarios = userService.findAll();
		
		//miramos si el artículo está precargado.
		if (articulo.getContenido().startsWith("/")) { // para ver si la observacion comienza con una url
			
			
			/*
			 * ESTO VA A SER UN TEST PARA PODER CARGAR CONTENIDO DESDE LA URL DE LA OBSERVACION/CONTENIDO
			 * EN LA BBDD de los Articulos ya precargados. como he dicho esto es una prueba.
			 * 
			 * 
			 * 1 - obtengo la url del articulo
			 * 2 - lo configuro como un ClassPathResource
			 * 3 - pongo una variable de String para pasarle a la vista mediante el modelo. esto con la funcion: StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
			 * 
			 * */
			//1
			String urlArchivo= articulo.getContenido(); 
			//2
			ClassPathResource recurso = new ClassPathResource("static"+urlArchivo);
			//3
			articulo.setContenido(StreamUtils.copyToString(recurso.getInputStream(), StandardCharsets.UTF_8));  // aquí el articulo tendría el contenido ya cargado desde la url
			
			
			// tenía la intencion de guardarlo pero no creo que haga falta si antes se veía directamente
			
			wikiService.saveArticulo(articulo);  // esto guarda el artículo en la wiki, puede que esto sea lo que me da problemas???
			
			flash.addFlashAttribute("error", "El articulo se ha cargado desde una url ya puedes ir a verlo");
			//return "articulo/ver"; // aquí lo estoy retornando sin haberlo pasado al modelo, por eso al haberse guardado ya en la BBDD ahí si me lo carga en la puta vista!!!
			
			
		}
		
		
		
		modelo.addAttribute("lista_usuarios", usuarios);  // se pasan los usuarios a la vista
		modelo.addAttribute("articulo", articulo);  // se pasa el articulo a la vista
		modelo.addAttribute("titulo", "Estás opinando sobre: "+ articulo.getTitulo());
		
		// Verificar si el usuario está autenticado antes de compronbar si es el coordinador de ka wiki a la que pertenece el artícuo
	    boolean esCoordinador = false;
	    if (authentication != null && authentication.isAuthenticated()) {
	        Usuario usuarioCoordinador = userService.esUsuarioCoordinador(articuloId, authentication);
	        		esCoordinador = usuarioCoordinador != null;
	    }
	

	    modelo.addAttribute("esCoordinador", esCoordinador);
		
		return "articulo/ver";
	}
	
	@GetMapping("/formulario/{wikiId}")
	public String crear(@PathVariable(value = "wikiId") Long wikiId,  Model modelo, RedirectAttributes flash) {
		
		Wiki wiki = wikiService.findOne(wikiId);
		
		if (wiki == null) { // nos aseguramos de que el cliente exista
			
			flash.addFlashAttribute("error", "La Wiki no existe en la BBDD");
			return "redirect:/listar";
		}
		
		Articulo articulo = new Articulo(); // creamos el articulo
		articulo.setWiki(wiki);    // un articulo debe ser para una wiki.
		modelo.addAttribute("articulo", articulo); // se le pasa el articulo a la vista
		modelo.addAttribute("titulo", "Formulario de Articulo");
		return "articulo/formulario"; // devolvemos la vista
	}
	
	

	
	/**
	 * vamos a crera el método para guardar el artículo en la BBDD
	 * 
	 */
	@PostMapping("/formulario")
	public String guardarArticulo( @Valid Articulo articulo,BindingResult resultado, Model modelo,RedirectAttributes flash, SessionStatus status ) {
		
		if (resultado.hasErrors()) {
			modelo.addAttribute("titulo", "Iluminanos con tu sabiduría!!");
			return "articulo/formulario";
		}
		
		if (articulo.getContenido() == null) {
			
		}
		
		wikiService.saveArticulo(articulo); //aquí se guarda registro de los articulos
		
		status.setComplete();
		
		flash.addFlashAttribute("success", "Articulo creado con éxito!");
		return "redirect:/ver/"+ articulo.getWiki().getId();
		
	}
	
	
	@PostMapping("/propuesta")
	public String guardarPropuesta(@RequestParam("file") MultipartFile file, @ModelAttribute Articulo articulo,RedirectAttributes flash) throws IOException {
		
		byte[] archivo = file.getBytes();
		PropuestaModificacion nuevaPropuesta = new PropuestaModificacion(); // YA VIENE CON EL ESTADO 'PENDIENTE'
		String contenido = new String(archivo, StandardCharsets.UTF_8);   // aquí meto el contenido del archivo en un string
		
		// se obtiene el usuario autenticado y se guarda la propuesta en su lista
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	    Usuario usuario = userService.findOne(userDetails.getUserId());
	    
	    nuevaPropuesta.setPropuesta(contenido);
		nuevaPropuesta.setArticulo(articulo);  // aquí se pone para que artículo viene dicha propuesta
	    nuevaPropuesta.setUsuario(usuario); // le paso el nombre del usuario autenticado ///
	    propuestaService.save(nuevaPropuesta); // guardamos la propuesta
	    
	    //Recupero la propuesta para que no vaya a haber dos propuestas repetidas
	    PropuestaModificacion propuestaGuardada = propuestaService.findOne(nuevaPropuesta.getId());
	    
	    usuario.getModificacionesPropuestas().add(propuestaGuardada); // le añado la nueva propuesta
	    userService.save(usuario);  // guardo el usuario
	    
	   

		// ahora añadimos la propuesta al articulo
		articulo.getPropuestas().add(propuestaGuardada);
		
		wikiService.saveArticulo(articulo);  // guardo el artículo con la propuesta ya añadida
		flash.addFlashAttribute("success", "Propuesta creada con éxito!");
		return"redirect:/articulo/ver/"+articulo.getId();
	}
	
	/**
	 * Método para modificar un artículo
	 */
	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable(value = "id") Long articuloId,  Model modelo, RedirectAttributes flash,  HttpServletResponse response) {
		
		Articulo articulo = wikiService.findArticuloById(articuloId); // encontramos el artículo a modificar
		if (articulo == null) {
			
			flash.addAttribute("error", "El artículo no existe en la BBDD");
			return "redirect:/listar";
		}
		
		
		
	    //fin de la prueba para descargar el archivo html
		
		modelo.addAttribute("articulo", articulo); // pasamos el artículo a la vista
		modelo.addAttribute("mensaje", "Sube una propuesta de modificación de este Artículo");
		return "articulo/propuesta";
		
	}
	
	
	@Secured("ROLE_GESTOR") // pueden pasarse un array de roles
	@RequestMapping(value = "/editar/{id}") // le vamos a pasar el id por parámetro a la url y hace una pedición GET
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> modelo, RedirectAttributes flash) {

		Articulo articulo = null;
		/*
		 * Ahora tenemos que mirar que el id sea mayor que cero y por lo tanto válido
		 */
		if (id > 0) {
			articulo = wikiService.findArticuloById(id); // se llama al método de la Clase DAO que por detrás llama al objeto
													// Entity mannager con su método find()

			// si el ID del artículo pasado por parámetro no existe se pasa un mensaje de
			// error
			if (articulo == null) {

				flash.addFlashAttribute("error", "El artículo no existe en la BBDD!");
				return "redirect:/listar";
			}

		} else {
			flash.addFlashAttribute("error", "El ID del artículo no puede ser cero!");
			return "redirect:/listar";
		}

		modelo.put("articulo", articulo); // se pasa el artículo a la vista.

		modelo.put("titulo", "Editar Artículo");

		return "articulo/formulario"; // me lleva a la vista del formulario con ese artículo?

	}
	
	
	
	/**
	 * implementamos el método para borrar facturas de un cliente
	 */
	@GetMapping("/eliminar/{id}")  //ojo porque este mapping es para mapear el método para que pueda ser invocado desde la vista!!
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		Articulo articulo = wikiService.findArticuloById(id);
		
		if (articulo != null) {
			
			wikiService.deleteArticulo(id);
			flash.addFlashAttribute("success", "Articulo Eliminado con Éxito!!");
			return "redirect:/ver/"+articulo.getWiki().getId();
			
		}
		
		flash.addFlashAttribute("error", "El Articulo no existe en la BBDD , no ha podido ser eliminado");
		return"redirect:/listar";
	}
		
}
