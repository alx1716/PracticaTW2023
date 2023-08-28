package com.proyectospring.app.models.service;

import java.util.List;


import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;


/**
 * Interfaz Service necesaria para el acceso a los datos 
 * Esto nos ayuda a implementar el patrón de diseño Facade que crea un fachada de acceso 
 * de esta forma no se accede directamente a los Dao desde los controladores
 * lo cual supone una buena práctica.
 */
public interface IArticuloService {
	
	 public List<Articulo> findAll();  // retorna una lista de wikis.
		
		public void save(Articulo articulo); // para guardar un articulo dentro de la tabla
		
		public Articulo findOne(Long id);  // para encontrar un artículo por su ID en la BBDD
		
		public void delete(Long id); // elimina un artículo de la BBDD
			
		
		public void savePropuesta(PropuestaModificacion propuesta);  // método para guardar la propuesta creada en la tabla de propuestas
		
		
		
		public PropuestaModificacion findPropuestaModificacionById(Long id); // método para encontrar una propuesta y ver el detalle de la misma. 
		
		public void deletePropuesta(Long id); // método para eliminar la propuesta.
		
		public void deleteByArticulo(Articulo articulo);
		
		

}
