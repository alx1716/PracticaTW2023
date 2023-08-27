package com.proyectospring.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyectospring.app.models.entity.Wiki;
import com.proyectospring.app.models.entity.Articulo;


public interface IWikiService {
	
    public List<Wiki> findAll();  // retorna una lista de wikis.
	
	public void save(Wiki wiki); // para guardar una wiki dentro de la tabla
	
	public Wiki findOne(Long id);  // para encontrar una wiki por su ID en la BBDD
	
	public void delete(Long id); // elimina una wiki de la BBDD
	
	
	public Page<Wiki> findAll(Pageable pageable);  // para paginar los registros obtenidos y mejorar la experiencia de usuario muestra una lista de resultados y pone página siguiente y demás.
	
	
	
	public void saveArticulo(Articulo articulo);  // método para guardar el articulo creado en la tabla de articulos
	
	
	
	public Articulo findArticuloById(Long id); // método para encontrar una factura y ver el detalle de la misma. 
	
	public void deleteArticulo(Articulo articulo); // método para eliminar la factura y sus líneas.
	
}
