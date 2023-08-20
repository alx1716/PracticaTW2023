package com.proyectospring.app.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.enums.PeticionStatus;
import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.models.entity.Role;
import com.proyectospring.app.models.entity.Usuario;



public interface IRoleDao extends CrudRepository<Role, Long>{
	
	public Role findByAuthority(String auth);

	public Role findByUsuario(Usuario usuario);
	
	@Query("SELECT u, r.newRole, r.status FROM Usuario u JOIN u.roles r WHERE r.newRole IS NOT NULL")
    public List<Usuario> findUsersWithNewRoles();

	public Role findByUsuarioAndNewRoleAndStatus(Usuario usuario, RoleEnum newRole, PeticionStatus pendiente);

	public boolean existsByUsuarioAndStatus(Usuario usuario, PeticionStatus pendiente);



}
