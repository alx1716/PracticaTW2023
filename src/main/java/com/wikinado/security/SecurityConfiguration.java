package com.wikinado.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.wikinado.config.ManejoAccesoDenegado;
import com.wikinado.security.service.ServiceSecurUserImpl;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	ServiceSecurUserImpl securUser;
	
	//metodo que inyecta un password encoder para encriptación de contraseñas
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	//metodo para inyectar un manejo de accesos a rutas denegadas
	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new ManejoAccesoDenegado();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//configuramos las rutas que queremos que sean de acceso público
		http.authorizeRequests().antMatchers("/", "/index","/denegado", "/layouts", "/login", "/loginSuccessful",
			"/user/registro", "/user/saved", "/wikis", "/wikis/**").permitAll()
			//para el resto de accesos a rutas derivamos al registro u login
			.anyRequest().authenticated().and().formLogin().loginProcessingUrl("/logueo").loginPage("/login").permitAll().defaultSuccessUrl("/loginSuccessful").failureUrl("/login?error=true")
			//manejo del login
			.usernameParameter("email").passwordParameter("password")
			//manejo del logout
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securUser).passwordEncoder(passwordEncoder());
	}

	
	
	
}
