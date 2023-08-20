package com.proyectospring.app.controllers;

import java.util.Arrays;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

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
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.service.CustomUserDetails;
import com.proyectospring.app.models.service.IUsuarioService;
import com.proyectospring.app.models.service.JpaUserDetailsService;


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
	
	@GetMapping("/perfil")
	public String perfil(Authentication authentication, Model modelo) {
		
		//recupero la información del usuario autenticado y lo recupero de la BBDD mediante el ID
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	    Long userId = userDetails.getUserId();
	    RoleEnum[] rolesEnum = RoleEnum.values();
	    Usuario usuario = usuarioService.findOne(userId);
	    modelo.addAttribute("usuario", usuario);
	    modelo.addAttribute("rolesEnum", rolesEnum);
	    modelo.addAttribute("usuario", usuarioService.findOne(userId)); // este es el usuario para poder acceder a TODA la información de dicho usuario
		return "perfil";
	}    
	 
	
	
	

	//metodo que devuelve lista de usuarios y roles
		@GetMapping("/gestor_user")
		public String listarUsuarios(Model model) {
	        List<Usuario> listaUsuarios = usuarioserv.userList();
	        List<String> predefinedRoles = Arrays.asList("COLABORADOR", "SUPERVISOR", "COORDINADOR", "GESTOR"); 
	        List<Usuario> usuariosConPropuestas = roleDao.findUsersWithNewRoles();
	        model.addAttribute("usuariosConPropuestas", usuariosConPropuestas);
	        model.addAttribute("listaUsuarios", listaUsuarios);
	        model.addAttribute("predefinedRoles", predefinedRoles);
	        return "gestor_user";
		}
		
		
		
	//método que añade un rol nuevo a un usuario existente
		@Transactional
		@PostMapping("/asigna_rol")
		public String asignarRol(@RequestParam("userId") Long userId,
				
	    @RequestParam("nuevoRol") String nuevoRol) {
			Optional<Usuario> usuarioOptional = usuarioserv.findUsuarioById(userId);
			if (usuarioOptional.isPresent()) {
		        Usuario usuario = usuarioOptional.get();
		        
		        // Verificar si el usuario ya tiene el rol
		        boolean hasRole = usuario.getRoles().stream()
		                           .anyMatch(role -> role.getAuthority().equals(nuevoRol));
		        
		        if (hasRole) {
		            // El usuario ya tiene el rol, redirigir con mensaje de error
		            return "redirect:/resultado_asigna_rol?success=false&userId=" + userId;
		        } else {	
			//String sql = "UPDATE authorities  SET `authority` = ? WHERE `user_id` = ?";
			String sql = "INSERT INTO `authorities` (user_id, authority) VALUES (?,?)";
		    Query query = entityManager.createNativeQuery(sql)	
		    .setParameter(1, userId)
		    .setParameter(2, nuevoRol);
		        
		
		query.executeUpdate();
		
		return "redirect:/resultado_asigna_rol?success=true&userId=" + userId;
		        } 
			}else {
		            // Manejar el caso en el que no se encontró el usuario
		            return "error_403";
		        }
	}

	//método para mostrar el resultado de la asignación de un nuevo rol
		@GetMapping("/resultado_asigna_rol")
		public String mostrarResultadoAsignacionRolResult(@RequestParam("success") boolean success,
		                                                  @RequestParam("userId") Long userId,
		                                                  Model model) {
		    Optional<Usuario> usuarioOptional = usuarioserv.findUsuarioById(userId);
		    
		    if (usuarioOptional.isPresent()) {
		        Usuario usuario = usuarioOptional.get();
		        List<Role> usuarioRoles = usuario.getRoles();
		        model.addAttribute("asignacionExitosa", success);
		        model.addAttribute("usuarioRoles", usuarioRoles);
		        
		        return "asigna_rol";
		    } else {
		        // Manejar el caso en el que no se encontró el usuario
		        return "error_403"; 
		    }
		}

	//método que permite eliminar un usuario determinado
		@Transactional
		 @PostMapping("/eliminar_usuario")
		    public String eliminarUsuario(@RequestParam("userId") Long userId) {
			 String deleteRolesSql = "DELETE FROM authorities WHERE user_id = ?";
			 String deleteUserSql = "DELETE FROM usuarios WHERE id = ?";
			 
			 Query deleteRolesQuery = entityManager.createNativeQuery(deleteRolesSql)
		            .setParameter(1, userId);
			 	
		     Query deleteUserQuery = entityManager.createNativeQuery(deleteUserSql)
		            .setParameter(1, userId);

		        

		        deleteRolesQuery.executeUpdate();
		        deleteUserQuery.executeUpdate();


		        return "redirect:/gestor_user"; // Redirige a la página de la lista de usuarios después de eliminar
		    }

	//método que atiende la llamada a la creación de un usuario nuevo
		@GetMapping("/crear")
	    public String mostrarFormularioCreacion(Model model) {
	        Usuario nuevoUsuario = new Usuario();
	        model.addAttribute("nuevoUsuario", nuevoUsuario);
	        return "crear_usuario"; 
	    }
	/*
		@PostMapping("/actualizar-rol")
	    public String actualizarRol(@RequestParam Long userId, @RequestParam String action) {
	        Usuario usuario = usuarioService.findOne(userId);
	        
	        if (usuario != null && usuario.getRoles().size() > 0) {
	            Role role = usuario.getRoles().get(0);
	            if ("accept".equals(action)) {
	                role.setStatus(PeticionStatus.ACEPTADA);
	                role.setAuthority(role.getNewRole().toString());
	            } else if ("reject".equals(action)) {
	                role.setStatus(PeticionStatus.RECHAZADA);
	            }
	            usuarioService.save(usuario);
	        }
	        
	        return "redirect:/gestor_user"; // Redirigir a la página de usuarios actualizada
	    } 
	    
	    */
		@Transactional
		@PostMapping("/actualizar-rol")
		public String actualizarRol(@RequestParam Long userId, @RequestParam String action) {
		    Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioService.findOne(userId));

		    if (usuarioOptional.isPresent()) {
		        Usuario usuario = usuarioOptional.get();
		        List<Role> roles = usuario.getRoles();

		        if (roles.size() > 0) {
		            Role role = roles.get(0);

		            if ("accept".equals(action)) {
		                role.setStatus(PeticionStatus.ACEPTADA);
		                RoleEnum newRole = role.getNewRole();
		                if (newRole != null && !usuarioHasRole(usuario, newRole)) {
		                    // Aquí inserta el nuevo rol si el usuario no lo tiene
		                    // (similando tu lógica de asignarRol)
		                    String sql = "INSERT INTO `authorities` (user_id, authority) VALUES (?,?)";
		                    Query query = entityManager.createNativeQuery(sql)
		                            .setParameter(1, userId)
		                            .setParameter(2, newRole.toString());
		                    query.executeUpdate();
		                }
		            } else if ("reject".equals(action)) {
		                role.setStatus(PeticionStatus.RECHAZADA);
		            }

		            usuarioService.save(usuario);
		        }
		    }

		    return "redirect:/gestor_user"; // Redirigir a la página de usuarios actualizada
		}

		private boolean usuarioHasRole(Usuario usuario, RoleEnum roleEnum) {
		    return usuario.getRoles().stream()
		            .anyMatch(role -> role.getAuthority().equals(roleEnum.toString()));
		}
}
		

