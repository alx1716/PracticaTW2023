package com.proyectospring.app.controllers;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectospring.app.models.entity.Wiki;
import com.proyectospring.app.models.service.IWikiService;
import com.proyectospring.app.models.service.IUploadFileService;
import com.proyectospring.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente") // para guardar las sesiones de actualización o creación de los usuarios ya así
								// saber si estamos editando un registro o creando uno nuevo
//OJO!!! si no hay pegas esto es para borrar!!! @RequestMapping("/") // se pasa el objeto a la sesion y este va permanecer ahí hasta que se llame al
						// método guardar
public class WikiController {

	@Autowired
	// esta es la implementación concreta que va a
	// inyectar. // lo he comentado porque al inyectar la clase de servicio en vez
	// del DAO directamente me da error
	private IWikiService wikiService;
	
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	
	
	@Secured("ROLE_COLABORADOR") // Para dar seguridad a los controladores en vez de hacerlo en la clase de configuración de seguridad
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	//@Secured("ROLE_COLABORADOR")
	@GetMapping(value = "/ver/{id}") // Método para poder ver la foto con el controlador
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> modelo, RedirectAttributes flash) {

		Wiki wiki = wikiService.findOne(id);

		if (wiki == null) {

			flash.addAttribute("error", "la wiki no existe en la base de datos");
			return "redirect:/listar";

		}

		modelo.put("wiki", wiki);
		modelo.put("titulo", "Dentro de la Wiki: " + wiki.getNombre());

		return "ver";

	}

	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "pagina", defaultValue = "0") int pagina, Model model) {
		
	
		

		Pageable paginaRequest = PageRequest.of(pagina, 5); // mostrará 5 registro por página

		Page<Wiki> listaDeWikis = wikiService.findAll(paginaRequest); // lista de las wikis por página

		PageRender<Wiki> paginador = new PageRender<Wiki>("/listar", listaDeWikis); // pasamos la url de la
																								// vista y la lista
																								// paginable

		model.addAttribute("titulo", "Cuñaduras Notables");

		model.addAttribute("pagina", paginador); // pasamos el paginador a la vista

		model.addAttribute("listado_wikis", listaDeWikis);

		return "listar";

	}

	@Secured("ROLE_GESTOR")
	@RequestMapping(value = "/formulario")
	public String crear(Map<String, Object> modelo) { // Cuando se crea una nueva wiki en el "crear" viene con el id
														// null

		Wiki wiki = new Wiki();
		modelo.put("wiki", wiki); // se pasa el usuario a la vista.

		modelo.put("titulo", "Formulario de Wiki");

		return "formulario";

	}

	/**
	 * Vamos a crear un nuevo método para editar los usuarios de la vista "Listar"
	 * aunque el método crear es parecido sólo crea el objeto y se lo pasa a la
	 * vista
	 * 
	 * Este método editar lo que va a hacer es buscar una wiki en la base de datos
	 * y modificarla esto lo va a hacer medainte la clase DAO que es la que se
	 * encarga del acceso a los datos
	 * 
	 */
	@Secured("ROLE_GESTOR") // pueden pasarse un array de roles
	@RequestMapping(value = "/formulario/{id}") // le vamos a pasar el id por parámetro a la url y hace una pedición GET
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> modelo, RedirectAttributes flash) {

		Wiki wiki = null;
		/*
		 * Ahora tenemos que mirar que el id sea mayor que cero y por lo tanto válido
		 */
		if (id > 0) {
			wiki = wikiService.findOne(id); // se llama al método de la Clase DAO que por detrás llama al objeto
													// Entity mannager con su método find()

			// si el ID de la Wiki pasado por parámetro no existe se pasa un mensaje de
			// error
			if (wiki == null) {

				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/listar";
			}

		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/listar";
		}

		modelo.put("wiki", wiki); // se pasa la wiki a la vista.

		modelo.put("titulo", "Editar Wiki");

		return "/formulario"; // me lleva a la vista del formulario con ese cliente?

	}

	// este método recibe los datos del nuevo cliente que vamos a validar ya
	// poblados del formulario.
	// si la clase cliente no se llama como el atributo con que lo pasamos a la
	// vista en el método "crear" se anota con @ModelAttribute("nombreDelAtributo")
	//@PreAuthorize("hasRole('ROLE_GESTOR', 'ROLE_COLABORA')")// se puede usar tambien @Secured("ROLE_GESTOR")
	@RequestMapping(value = "/formulario", method = RequestMethod.POST) // Para devolver al cliente al formulario en
																		// caso de que haya errores se usa el
																		// BindingResult OJO el objeto mapeado y el
																		// BindingResult SIEMPRE van juntos en este
																		// orden.
	public String guardar(@Valid Wiki nuevaWiki, BindingResult resultado, Model modelo,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
		if (resultado.hasErrors()) {

			modelo.addAttribute("titulo", "Formulario de Wiki");
			return "formulario";
		}

		/*if (!foto.isEmpty()) {

			if (nuevaWiki.getId() != null && nuevaWiki.getId() > 0 && nuevaWiki.getFoto() != null
					&& nuevaWiki.getFoto().length() > 0) {

				uploadFileService.delete(nuevaWiki.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			nuevaWiki.setFoto(uniqueFilename);
		} else {
			nuevaWiki.setFoto("NO HAY FOTO"); // CON ESTO EVITO QUE ME SALTE UN ERROR AL NO HABER FOTO EN EL CLIENTE.
		}*/

		String mensajeFlash = (nuevaWiki.getId() != null) ? "Wiki editada con éxito"
				: "Wki creada con éxito!"; // para ver si se ha creado o editado.

		wikiService.save(nuevaWiki);// aquí la clase DAO accede a la base de datos y guarda el el objeto de la
											// clase que está mapeada a esa tabla.
		status.setComplete(); // se debe cerrar la sesion despues de guardar, esto elimina el objeto cliente
								// de la session y termina el proceso.

		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/listar"; // redirige a la lista enseñando la nueva Wiki.
	}

	/**
	 * Ahora creamos el método que va a usar el controlador para eliminar un usuario
	 * mediante el uso de la clase DAO
	 * 
	 * 
	 */
	@PreAuthorize("hasRole('ROLE_GESTOR')")// se puede usar tambien @Secured("ROLE_GESTOR")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Wiki wiki = wikiService.findOne(id); // para obtener al cliente que se quiere tratar.

			wikiService.delete(id);
			flash.addFlashAttribute("success", "Wiki eliminada con éxito!");

			
			// antes deliminar debemos validar
			
				/*if (uploadFileService.delete(wiki.getFoto())) {
					flash.addAttribute("info", "Foto " + wiki.getFoto() + " Eliminada con éxito");

				}*/

			
		}
		return "redirect:/listar"; // OJO que si no pongo el '/' falla en redirigir la vista.

	}
	
	/**
	 * Método para autyenticar si el usuario posee algún rol
	 */
	public boolean hasRole( String role) {
		
		SecurityContext context = SecurityContextHolder.getContext(); 
		
		//se valida si el objeto es nulo
		if (context == null) {
			
			return false;
		}
		
		Authentication auth = context.getAuthentication(); // a través del objeto auth vamos a obtener una coleccion de roles o de authorities
		
		if (auth == null) {
			
			return false;
		}
		
		Collection< ? extends GrantedAuthority> authorities = auth.getAuthorities(); //obtenemos la lista de roles...OJO a la clase que deben extender los roles
		
		
		
		//esta sería una forma de validar recorriendo una lista de los roles que dispone el usuario
		/*for (GrantedAuthority authority : authorities) {
			
			if (role.equals(authority.getAuthority())) {	 //si el rol se encuentra entre los roles se devuelve true.
				
				return true;
				
			}
			
		}
		return false;*/
		
		//otra forma de validar más resumida sería
		return authorities.contains(new SimpleGrantedAuthority(role)); // simpleGrantedAuthoritie es una implementacion concreta de GRantedAuthorieties
	}
}
