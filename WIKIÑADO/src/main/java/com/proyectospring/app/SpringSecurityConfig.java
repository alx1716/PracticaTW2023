package com.proyectospring.app;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyectospring.app.auth.handler.LoginSuccessHandler;
import com.proyectospring.app.models.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)//SE habilita el uso de anotaciones en la clase configuration y poder usar anotaciones de seguridad en los controladores
@Configuration
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter{    // una alternativa a esta deprecated es WebSecurityConfigurer
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//inyectamos la clase que implementa el userDetailsService
	@Autowired
	JpaUserDetailsService userdetails;
	
	
	//inyectamos el succesHandler para los mensajes flash a la hora de logearse
	@Autowired
	private LoginSuccessHandler succesHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//aquí se configuran los accesos a la app EN EL CONTROLADOR?
		http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**","/listar", "/registro", "/ver/**","/home", "/articulo/ver/**","/doc/**","/articulos/**","/pdf/**","/static/**","/layout/**" ).permitAll() // A ESTAS PaGINAS TENDRaN ACCESO TODOS LOS USUARIOS ESTeN O NO REGISTARDOS
		.anyRequest().authenticated()
		//se configura el Log in y el Log out
		.and()
		   .formLogin()
		   		.successHandler(succesHandler)
		   		.loginPage("/login") // esta es la ruta del metodo en el controlador de inicio de sesion.
		   		.defaultSuccessUrl("/comprobar") // aquí se redirige despues de un inicio de sesion exitoso
		   		.usernameParameter("email")
		   .permitAll()
		.and()
		.logout().logoutSuccessUrl("/home") // aqui se redirige despues de cerrar sesion
		.permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
		
		
		
		
	}

	
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder)  throws Exception{
		
		builder.userDetailsService(userdetails).passwordEncoder(passwordEncoder);
		
		
	}

	
}
