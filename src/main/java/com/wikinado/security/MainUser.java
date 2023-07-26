//se crea un MainUser que es el usuario securizado de spring security
//implementa USerDetails que maneja internamete spring
package com.wikinado.security;


import java.util.Collection;
import java.util.List;


//Esta clase va implementar la interfaz  UserDetails de Spring Security
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetails;

import com.wikinado.entities.Rol;
import com.wikinado.entities.Usuario;



public class MainUser implements UserDetails{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//propiedades
		private long id;
		private String email;
		private String nombre;
		private String password;
		//creamos una coleccion de autoridades o roles que herada de GrantedAuthority
		//Son los roles que maneja Spring Security
		private Collection<? extends GrantedAuthority> autoridades;
		
		
		//constructor con parámetros
		public MainUser(long id, String email, String password, String nombre,
				Collection<? extends GrantedAuthority> autoridades) {
			this.id = id;
			this.email = email;
			this.password = password;
			this.nombre = nombre;
			this.autoridades = autoridades;
		}

		//método que empareja mis usuarios creados para aplicación con los que provisiona Spring security
		//método que devuelve el usuario principal pasandole como argumento una Entidad Usuario
		public static MainUser creador(Usuario user) {
		    List<GrantedAuthority> autoridades = new ArrayList<>();

		    for (Rol rol : user.getRoles()) {
		        GrantedAuthority authority = new SimpleGrantedAuthority(rol.getTipoRol().toString());
		        autoridades.add(authority);
		    }

		    return new MainUser(user.getId(), user.getEmail(), user.getPassword(), user.getNombre(), autoridades);
		}
		
		//metodo que deuelve la coleccion de autoridades
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			return autoridades;
		}
		//metodo que devuelve el password
		@Override
		public String getPassword() {
			
			return password;
		}
		//metodo que devuelve el nombre de usuario
		public String getEmail() {
			
			return email;
		}
		@Override
		public boolean isAccountNonExpired() {
			
			return true;
		}
		@Override
		public boolean isAccountNonLocked() {
			
			return true;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			
			return true;
		}
		@Override
		public boolean isEnabled() {
			
			return true;
		}
		//metodo que devuelve el id del usuario
		public long getId() {
			return id;
		}

		public String getNombre() {
			return nombre;
		}
		
		public String getUsername() {
			return nombre;
		}

		
}
