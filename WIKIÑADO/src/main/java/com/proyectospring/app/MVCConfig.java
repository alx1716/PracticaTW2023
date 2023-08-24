package com.proyectospring.app;

import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		

	
	}
	
	
	
	/**
	 * método para implementar un controlador de vistas sin necesidad de crear un controlador para 
	 * los errores de acceso a los usuarios que no tienen el rol necesario
	 * hablaos de los errores que derivan en error 403  Video 129
	 */
	
	public void addViewControllers(ViewControllerRegistry registry) { // este método lo configuramos con la ruta que le hemos dado al addViewController en la clase de configuración de spring security
		
		registry.addViewController("/error_403").setViewName("error_403");// este vista "error_403" la crearemos.
		
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
