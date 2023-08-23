package com.proyectospring.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.proyectospring.app.models.entity.Role;
import com.proyectospring.app.models.entity.Usuario;



public interface IRoleDao extends CrudRepository<Role, Long>{
	
	public Role findByAuthority(String auth);

	public Role findByUsuario(Usuario usuario);
	
	




}
