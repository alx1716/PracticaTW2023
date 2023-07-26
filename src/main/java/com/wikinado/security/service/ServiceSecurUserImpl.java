//implementación para servicio usuario
package com.wikinado.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wikinado.entities.Usuario;
import com.wikinado.security.MainUser;
import com.wikinado.service.UsuarioService;
@Service
public class ServiceSecurUserImpl  implements UserDetailsService {
	@Autowired
	//declaramos un usuario del servicio
	private UsuarioService usuarioserv;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//llamamos al metodo que devuelve un usuario pasandole el nombre
		//si no lo encuentra lanza una excepcion
		Usuario usuario = usuarioserv.userEmail(email).orElseThrow(() -> new UsernameNotFoundException(email) );
		//devolver el método creador del usuario main
		return MainUser.creador(usuario);
	}

	
	
}
