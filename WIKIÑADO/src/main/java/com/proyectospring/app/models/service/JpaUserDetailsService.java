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

		// 2- obtenemos sus roles
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); // creamos una lista para guardar los
																				// roles del usuario

		for (Role rol : usuario.getRoles()) {

			authorities.add(new SimpleGrantedAuthority(rol.getAuthority())); // se añaden los roles de dicho usuario, ya que esto sólo es el login y este usuario puede tener mucho roles asignados
		}

		if (authorities.isEmpty()) {
			

			throw new UsernameNotFoundException("Error de login para el email: " + email + " NO tiene roles asignados");
		}

		// se devuelve el usuario autenticado // esto lo tengo que checkear a ver si me sirve así o al cambiar al email como forma de login pasa algo.
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


}
