package com.proyectospring.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectospring.app.models.dao.IPropuestaModificacionDao;
import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;
import com.proyectospring.app.models.entity.Usuario;


@Service
public class PropuestaModificacionServiceImpl implements IPropuestaModificacionService {
	
	@Autowired
	private IPropuestaModificacionDao propuestaDao;

	@Override
	@Transactional(readOnly = true)
	public PropuestaModificacion findOne(Long id) {
		
		return propuestaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PropuestaModificacion> findAll() {
		
		return (List<PropuestaModificacion>) propuestaDao.findAll();
	}

	@Override
	@Transactional
	public void save(PropuestaModificacion propuesta) {
		
		propuestaDao.save(propuesta);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		propuestaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public PropuestaModificacion findByArticuloAndUsuario(Articulo articulo, Usuario usuario) {
		
		return propuestaDao.findByArticuloAndUsuario(articulo, usuario);
	}
	
	

}
