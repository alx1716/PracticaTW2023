package com.proyectospring.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Usuario;

/**
 * interfaz de acceso a los datos
 */
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username); // a traves del nombre del método se ejecuta la consulta.. OJO!!!
	
	
	public Usuario findByEmail(String email); // esto se puede hacer ya que la clave que vamos a mirar va a ser el correo?
	
	
	//buscar por id
	public Optional<Usuario> findById(Long id);
	
	//se devuelve booleano como resultado si lo ha encontrado o no
	boolean existsByEmail(String email);

}
