package com.proyectospring.app.models.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioArticulo;



public interface IUsuarioArticuloDao extends CrudRepository<UsuarioArticulo, Long> {

	
	
	public List<UsuarioArticulo> findALLByUsuarioId(Long id);
	
	public UsuarioArticulo findByUsuarioAndArticulo(Usuario user , Articulo articulo);  // para ver si un artículo está ya asignado a un usuario
	
	

}
