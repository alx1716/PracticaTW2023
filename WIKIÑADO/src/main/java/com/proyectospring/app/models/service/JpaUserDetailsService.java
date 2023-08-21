package com.proyectospring.app.models.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectospring.app.models.dao.IUsuarioDao;
import com.proyectospring.app.models.entity.Role;
import com.proyectospring.app.models.entity.Usuario;

@Service("JpaUserDetailsService") // OJO!!! sin esto me da problemas.
public class JpaUserDetailsService implements UserDetailsService { // en esta clase no se necesita una intefaz ya que
																	// esta la provee spring security

	@Autowired
	private IUsuarioDao usuarioaDao; // para buscar al usuario en la BBDD

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // Userdetails representa
																								// a un usuario
																								// autenticado

		// 1- Obtenemos el usuario
		Usuario usuario = usuarioaDao.findByEmail(email);

		if (usuario == null) {

			throw new UsernameNotFoundException("El usuario con email:  " + email + " NO existe en el sistema");
		}

		// 2- Obtenemos sus roles
		List<GrantedAuthority> authorities = new ArrayList<>(); // Creamos una lista para guardar los roles del usuario

		for (Role role : usuario.getRoles()) {
		    authorities.add(new SimpleGrantedAuthority(role.getAuthority().toString())); // Se añaden los roles de dicho usuario
		}

		if (authorities.isEmpty()) {
		    throw new UsernameNotFoundException("Error de login para el usuario con ID: " + usuario.getId() + " - No tiene roles asignados");
		}

		// Se devuelve el usuario autenticado
		return new CustomUserDetails(usuario.getUsername(), usuario.getPassword(), usuario.getId(), authorities);
	}
		
		//metodo para obtener listado de roles
		public Iterable<Usuario> rolList(){
			return usuarioaDao.findAll();
		}
		
		//método que devuelve el listado de usuarios y roles 16/08
		public List<Usuario> userList() {
			 List<Usuario> usuarios = (List<Usuario>) usuarioaDao.findAll();
			    return usuarios;
		}
		
		public Optional<Usuario> findUsuarioById(Long userId) {
			return usuarioaDao.findById(userId);
		}
		
		
		
		//metodo que almacena un usuario en BBDD
		public void saveUser(Usuario usuario){
			usuarioaDao.save(usuario);
		}

		public void delete(Usuario usuario) {
			usuarioaDao.delete(usuario);
			
		}


}
