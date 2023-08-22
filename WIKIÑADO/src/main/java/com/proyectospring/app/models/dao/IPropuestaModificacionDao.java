package com.proyectospring.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;
import com.proyectospring.app.models.entity.Usuario;

public interface IPropuestaModificacionDao extends CrudRepository<PropuestaModificacion, Long> {
	
	public PropuestaModificacion findByArticuloAndUsuario(Articulo articulo, Usuario usuario);

}
