package com.proyectospring.app.models.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
/**
 * Clase para poder customizar el retorno que me da el método
 * loadUserByUsername de la clase que extiende a UserDetailsService
 * así podré recuperar el id del usuario autenticado en ese momento 
 * y buscarlo en la BBDD para recuperar toda la info necesaria para el perfil: 
 * 	Toda la información personal
 * 	Articulos asignados
 * 	Propuestas realizadas
 * 
 */
public class CustomUserDetails  extends User{
	
	private Long userId;
	
	
	public CustomUserDetails(String username, String password, Long userId, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }
	
	
	public Long getUserId() {
        return userId;
    }

}
