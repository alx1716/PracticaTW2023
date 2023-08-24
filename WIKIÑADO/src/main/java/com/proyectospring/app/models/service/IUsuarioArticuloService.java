package com.proyectospring.app.models.service;

import java.util.List;

import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioArticulo;


public interface IUsuarioArticuloService {
	
	public List<UsuarioArticulo> findAllByUsuarioId(Long id); // para encontrar sólo los registros que estén relacionados a ese usuario
	
	public void save( UsuarioArticulo articuloAsignado);
	
	public UsuarioArticulo findOneById(Long id);
	
	public void deleteById(Long id);
	
	public UsuarioArticulo findByUsuarioAndArticulo(Usuario user , Articulo articulo);

	public void delete(UsuarioArticulo relacion); 

}
