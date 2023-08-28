package com.proyectospring.app.models.service;

import java.util.List;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioArticulo;

/**
 * Interfaz Service necesaria para el acceso a los datos 
 * Esto nos ayuda a implementar el patrón de diseño Facade que crea un fachada de acceso 
 * de esta forma no se accede directamente a los Dao desde los controladores
 * lo cual supone una buena práctica.
 */
public interface IUsuarioArticuloService {
	
	public List<UsuarioArticulo> findAllByUsuarioId(Long id); // para encontrar sólo los registros que estén relacionados a ese usuario
	
	public void save( UsuarioArticulo articuloAsignado);
	
	public UsuarioArticulo findOneById(Long id);
	
	public void deleteById(Long id);
	
	public UsuarioArticulo findByUsuarioAndArticulo(Usuario user , Articulo articulo); 

}
