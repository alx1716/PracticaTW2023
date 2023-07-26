package com.wikinado.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wikinado.dao.UsuarioDao;
import com.wikinado.entities.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	//método que devuelve el listado de usuarios
	public List<Usuario> userList(){
		return usuarioDao.findAll();
	}
	
	//metodo que devuelve un email pasandole el email de usuario como argumento
	public Optional<Usuario> userEmail(String email){
		return usuarioDao.findByEmail(email);
	}
	
	//metodo que devuelve un usuario pasandole el id como argumento
	public Optional<Usuario> userId(Long id){
		return usuarioDao.findById(id);
	}
		
	//metodo que almacena un usuario en BBDD
	public void saveUser(Usuario usuario){
		usuarioDao.save(usuario);
	}
	
	//metodo que verifica si un email de usuario existe o no
	public boolean emailExists(String email) {
		return usuarioDao.existsByEmail(email);
	}
	
	//metodo que verifica si un id de usuario existe o no
	public boolean userExists(Long id) {
		return usuarioDao.existsById(id);
	}
	
	
}
