package com.proyectospring.app.models.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.proyectospring.app.enums.PeticionStatus;
import com.proyectospring.app.models.entity.PeticionRol;

/**
 * interfaz de acceso a los datos
 */
public interface PeticionRolDao extends CrudRepository<PeticionRol, Long>{

	

	public List<PeticionRol> findByUsuarioIdAndStatus(Long userId, PeticionStatus pendiente);



}
