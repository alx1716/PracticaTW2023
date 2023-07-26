package com.wikinado.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wikinado.entities.Rol;
import com.wikinado.enums.TipoRol;

@Repository
public interface RolDao extends JpaRepository<Rol, Long>{
	
	//metodo para buscar un rol
	Optional<Rol> findByTipoRol(TipoRol tipoRol);
	//metodo para validar si existe un rol
	boolean existsByTipoRol(TipoRol tipoRol);
	
	
}
