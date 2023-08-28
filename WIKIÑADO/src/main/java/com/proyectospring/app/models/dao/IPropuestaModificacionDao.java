package com.proyectospring.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;
import com.proyectospring.app.models.entity.Usuario;
/**
 * interfaz de acceso a los datos
 */
public interface IPropuestaModificacionDao extends CrudRepository<PropuestaModificacion, Long> {
	
	public PropuestaModificacion findByArticuloAndUsuario(Articulo articulo, Usuario usuario);
	
	public void deleteByUsuario(Usuario usuario);
	
	public void deleteByArticulo(Articulo articulo);

}
