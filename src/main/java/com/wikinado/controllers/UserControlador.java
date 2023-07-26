package com.wikinado.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wikinado.entities.Rol;
import com.wikinado.entities.Usuario;
import com.wikinado.enums.TipoRol;
import com.wikinado.service.RolService;
import com.wikinado.service.UsuarioService;


@Controller
//definimos la raíz de la ruta para la clase
@RequestMapping("/user")
public class UserControlador {
	
	@Autowired
	private UsuarioService usuarioserv;
	
	//inyectamos un servicio de roles
	@Autowired
	private RolService rolService;
	
	@Autowired
	//rescato el password enconder
	PasswordEncoder passwordEncoder;
	
	
	//metodo que devuelve la ruta de registro
	@GetMapping("/registro")
	public String registro() {
		//devuelve la vista registro
		return "registro";
	}
	
	//metodo para guardar usuarios en la bbdd, recibe como parametros nombre usuario y password
	//además se incluye una llamada a model para intercambio de mensajes entre modelo y vista
	@PostMapping("/saved")
	public String guardaUser(String nombre, String email, String password, Model modelo) {
		if(usuarioserv.emailExists(email)) {
			modelo.addAttribute("emailRepetido", "El email ya ha sido usado");
			return "registro";
		}
		
		if(email == "" || password == "") {
			modelo.addAttribute("camposVacios", "Los campos no pueden estar vacíos");
			return "registro";
		}
		
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setNombre(nombre);
		//contraseña encriptada
		usuario.setPassword(passwordEncoder.encode(password));
		//extracción del rol colaborador en la variable rolTipo
		Rol rolTipo = rolService.obtenerRolTipo(TipoRol.colaborador).get();
		
		//set para añadir otro tipos de Rol
		Set<Rol> roles = new HashSet<Rol>();
		roles.add(rolTipo);
		
		//asignamos el rola al usuario actual
		usuario.setRoles(roles);
		//gurdamos al usuario mediante el servicio
		usuarioserv.saveUser(usuario);
		
		//mandamos mensaje al usuario para que se loguee
		//mandamos información a la vista mediante model
		modelo.addAttribute("OkRegistro", "Registro completo, por favor inicie sesión");
		//retornamos la página de login
		return "redirect:/login";
	}
	
}
