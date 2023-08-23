package com.proyectospring.app.controllers;

import java.util.Arrays;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectospring.app.enums.PeticionStatus;
import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.models.dao.IRoleDao;
import com.proyectospring.app.models.entity.Role;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioArticulo;
import com.proyectospring.app.models.entity.UsuarioWiki;
import com.proyectospring.app.models.entity.Wiki;
import com.proyectospring.app.models.service.CustomUserDetails;
import com.proyectospring.app.models.service.IArticuloService;
import com.proyectospring.app.models.service.IPropuestaModificacionService;
import com.proyectospring.app.models.service.IUsuarioArticuloService;
import com.proyectospring.app.models.service.IUsuarioService;

import com.proyectospring.app.models.service.JpaUserDetailsService;



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
	private JpaUserDetailsService usuarioserv;
	
	@Autowired
	private EntityManager entityManager; // Inyecta el EntityManager
	

	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IWikiService wikiService;
	
	
	@Autowired
	private IUsuarioWikiService usuarioWikiService;
	
	@Autowired
	private IUsuarioArticuloService usuarioArticuloService;
	
	@Autowired
	private IArticuloService articuloService;
	
	@Autowired
	private IPropuestaModificacionService propuestaModificacionService;
	

	
	@GetMapping("/perfil")
	public String perfil(Authentication authentication, Model modelo) {
		
		//recupero la información del usuario autenticado y lo recupero de la BBDD mediante el ID
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	    Long userId = userDetails.getUserId();
	    RoleEnum[] rolesEnum = RoleEnum.values();
	    Usuario usuario = usuarioService.findOne(userId);
	    List<PropuestaModificacion> propuestas = propuestaModificacionService.findAll();
	    modelo.addAttribute("propuestas", propuestas);
	    modelo.addAttribute("usuario", usuario);
	    modelo.addAttribute("rolesEnum", rolesEnum);
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

	

	//para enviar los datos necesarios a la vista
	@GetMapping("/gestor_user")
	public String listarUsuarios(Model model) {
	    List<Usuario> listaUsuarios = usuarioserv.userList();
	    
	    // Obtener los roles predefinidos del enumerado RoleEnum
	    List<String> predefinedRoles = Arrays.stream(RoleEnum.values())
	                                        .map(role -> role.toString())
	                                        .collect(Collectors.toList());

	    model.addAttribute("listaUsuarios", listaUsuarios);
	    model.addAttribute("predefinedRoles", predefinedRoles);
	    return "gestor_user";
	}
		
		
		//método que añade un rol nuevo a un usuario existente
		@Transactional
		@PostMapping("/asigna_rol")
		public String asignarRol(
		    @RequestParam("userId") Long userId,
		    @RequestParam("newRole") RoleEnum newRole,
		    RedirectAttributes redirectAttributes
		) {
		    Optional<Usuario> usuarioOptional = usuarioserv.findUsuarioById(userId);
		    
		    if (usuarioOptional.isPresent()) {
		        Usuario usuario = usuarioOptional.get();
		        
		        // Verificar si el usuario ya tiene el rol
		        boolean hasRole = usuario.getRoles().stream()
		                           .anyMatch(role -> role.getAuthority().equals(newRole));
		        
		        if (hasRole) {
		            // El usuario ya tiene el rol, mostramos mensaje de error
		        	redirectAttributes.addFlashAttribute("error", "El usuario "+ usuario.getUsername() +" ya tiene el rol solicitado, debes seleccionar otro rol.");
		            return "redirect:/gestor_user";
		        } else {
		            Role newRoleEntity = new Role();
		            newRoleEntity.setAuthority(newRole);
		            newRoleEntity.setUsuario(usuario);
		            
		            usuario.getRoles().add(newRoleEntity);
		            
		            usuarioserv.saveUser(usuario);  
		            //asignacion de rol correcta, mensaje flash para notificarlo
		            redirectAttributes.addFlashAttribute("success", "La asignación de Rol al usuario "+ usuario.getUsername()+", se ha realizado con éxito!");
		            return "redirect:/gestor_user";
		            
		        }
		    } else {
		        // Manejar el caso en el que no se encontró el usuario
		    	redirectAttributes.addFlashAttribute("error", "No se ha encontrado al usuario.");
	            return "redirect:/gestor_user";
		    }
		}
		
//método que permite eliminar un usuario determinado
		@Transactional
		@PostMapping("/eliminar_usuario")
		public String eliminarUsuario(@RequestParam("userId") Long userId,
										RedirectAttributes redirectAttributes) {
		    Optional<Usuario> usuarioOptional = usuarioserv.findUsuarioById(userId);
		    
		    if (usuarioOptional.isPresent()) {
		        Usuario usuario = usuarioOptional.get();
		        
		        usuarioserv.delete(usuario);  
		        // mensaje que indica que se ha eliminado el usuario correctamente
	        	redirectAttributes.addFlashAttribute("success", "El usuario "+ usuario.getUsername() +" se ha eliminado correctamente.");
		        return "redirect:/gestor_user"; 
		    } else {
		    	// mensaje que indica que no se puede eliminar porque no existe usuario
	        	redirectAttributes.addFlashAttribute("error", "El usuario no se puede eliminar, ya que no existe en el sistema.");
		        return "redirect:/gestor_user"; 
		    }
		}

	//método qpara la creación de un usuario nuevo
		@GetMapping("/crear")
	    public String mostrarFormularioCreacion(Model model) {
	        Usuario nuevoUsuario = new Usuario();
	        model.addAttribute("nuevoUsuario", nuevoUsuario);
	        return "crear_usuario"; 
	    }
}
		

