package com.proyectospring.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
public class PruebaBbddApplication extends SpringBootServletInitializer implements CommandLineRunner {

	

	/*@Autowired
	BCryptPasswordEncoder passwordEncoder;*/

	public static void main(String[] args) {
		SpringApplication.run(PruebaBbddApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception { // para eliminar y crear el directorio uploads cuando se inicie
														// nuestra aplicación.

		

		/*String password = "12345";

		for (int i = 0; i < 2; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}*/

	}

}
