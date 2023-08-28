package com.proyectospring.app.models.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioWiki;
import com.proyectospring.app.models.entity.Wiki;

/**
 * interfaz de acceso a los datos
 */
public interface IUsuarioWikiDao extends CrudRepository<UsuarioWiki, Long> {

	
	
	public List<UsuarioWiki> findALLByUsuarioId(Long id);
	
	public UsuarioWiki findByUsuarioAndWiki(Usuario user , Wiki wiki);  // para ver si una wiki está ya asignada a un usuario
	
	

}
