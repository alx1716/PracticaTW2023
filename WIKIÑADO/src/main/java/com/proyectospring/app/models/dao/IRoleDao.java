package com.proyectospring.app.models.dao;



import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Role;



public interface IRoleDao extends CrudRepository<Role, Long>{
	
	public Role findByAuthority(String auth);

}
