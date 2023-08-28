package com.proyectospring.app.models.service;

import java.util.List;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;
import com.proyectospring.app.models.entity.Usuario;

/**
 * Interfaz Service necesaria para el acceso a los datos 
 * Esto nos ayuda a implementar el patrón de diseño Facade que crea un fachada de acceso 
 * de esta forma no se accede directamente a los Dao desde los controladores
 * lo cual supone una buena práctica.
 */
public interface IPropuestaModificacionService {
	
	public PropuestaModificacion findOne(Long id);
	public List<PropuestaModificacion> findAll();
	
    public void save(PropuestaModificacion propuesta); // para guardar una propuesta dentro de la tabla
	
	public void delete(Long id); // elimina una propuesta de la BBDD
	
	public PropuestaModificacion findByArticuloAndUsuario(Articulo articulo, Usuario usuario); // para no tener registros repetidos en las tablas.
	
	public void deleteByUsuario(Usuario usuario);
	
	public void deleteByArticulo(Articulo articulo);

}
