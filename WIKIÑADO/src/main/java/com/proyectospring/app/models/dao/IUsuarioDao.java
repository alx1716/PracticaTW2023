package com.proyectospring.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username); // a traves del nombre del método se ejecuta la consulta.. OJO!!!
	
	
	public Usuario findByEmail(String email); // esto se puede hacer ya que la clave que vamos a mirar va a ser el correo?
	
	
	//IMPORTANTE: Por cada clase Dao debe haber una clase service.

}
