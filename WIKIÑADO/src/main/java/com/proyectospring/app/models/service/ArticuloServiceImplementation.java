package com.proyectospring.app.models.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectospring.app.models.dao.IArticuloDao;
import com.proyectospring.app.models.dao.IPropuestaModificacionDao;
import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;

/**
 * Clase que implementa la interfaz Service necesaria para el acceso a los datos 
 * de esta forma no se accede directamente a los Dao desde los controladores
 * lo cual supone una buena práctica
 */
@Service
public class ArticuloServiceImplementation implements IArticuloService {
	
	@Autowired
	private IPropuestaModificacionDao propuestaDao;
	@Autowired
	private IArticuloDao articuloDao;

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findAll() {
		
		return (List<Articulo>) articuloDao.findAll();
	}

	@Override
	@Transactional
	public void save(Articulo articulo) {
		articuloDao.save(articulo);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findOne(Long id) {
		
		return articuloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		articuloDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public void savePropuesta(PropuestaModificacion propuesta) {
		propuestaDao.save(propuesta);
		
	}

	@Override
	@Transactional(readOnly = true)
	public PropuestaModificacion findPropuestaModificacionById(Long id) {
	
		return propuestaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deletePropuesta(Long id) {
		propuestaDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public void deleteByArticulo(Articulo articulo) {
		
		propuestaDao.deleteByArticulo(articulo);
		
	}
	

	
	

}
