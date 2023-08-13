package com.proyectospring.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Role;
import com.proyectospring.app.models.entity.Usuario;


public interface IRoleDao extends CrudRepository<Role, Long>{
	
	public Role findByAuthority(String auth);

}
