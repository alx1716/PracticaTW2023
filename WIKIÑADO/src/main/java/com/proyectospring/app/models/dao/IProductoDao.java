package com.proyectospring.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {
	@Query("select p from Producto p where p.nombre like %?1%") // esta es la consulta personalizada que hará el método se seleccionan los productos donde el nombre sea igual al primer argumento del método.
	public List<Producto> findByNombre(String term);
	
	//public List<Producto> findByNombreLikeIgnoreCase(String term);
}
