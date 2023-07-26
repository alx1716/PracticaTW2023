package com.wikinado.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wikinado.dao.RolDao;
import com.wikinado.entities.Rol;
import com.wikinado.enums.TipoRol;

@Service
public class RolService {
	
	@Autowired
	private RolDao rolDao;
	
	//metodo para guardar un rol
	public void saveRol(Rol rol) {
		rolDao.save(rol);
	}
	
	//metodo para obtener un rol
	public Optional<Rol> obtenerRolTipo(TipoRol tipoRol){
		return rolDao.findByTipoRol(tipoRol);
	}
	
	//metodo para verificar si existe un rol
	public boolean existeTipoRol(TipoRol tipoRol){
		return rolDao.existsByTipoRol(tipoRol);
	}
}
