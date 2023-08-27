package com.proyectospring.app.models.service;

import java.util.List;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;
import com.proyectospring.app.models.entity.Usuario;


public interface IPropuestaModificacionService {
	
	public PropuestaModificacion findOne(Long id);
	public List<PropuestaModificacion> findAll();
	
    public void save(PropuestaModificacion propuesta); // para guardar una propuesta dentro de la tabla
	
	public void delete(Long id); // elimina una propuesta de la BBDD
	
	public PropuestaModificacion findByArticuloAndUsuario(Articulo articulo, Usuario usuario); // para no tener registros repetidos en las tablas.
	
	public void deleteByUsuario(Usuario usuario);
	
	public void deleteByArticulo(Articulo articulo);

}
