package com.proyectospring.app.models.service;

import java.util.List;

import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.models.entity.Usuario;


public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findOne(Long id);
	
	public Usuario findByUsername(String name);
	
	public void delete(Long id);
	
	public void save(Usuario user);
	
	void actualizarRol(Usuario usuario, RoleEnum nuevoRol);

}
