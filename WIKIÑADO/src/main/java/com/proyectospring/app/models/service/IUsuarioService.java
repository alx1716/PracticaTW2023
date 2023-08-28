package com.proyectospring.app.models.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.security.core.Authentication;

import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.models.entity.Usuario;

/**
 * Interfaz Service necesaria para el acceso a los datos 
 * Esto nos ayuda a implementar el patrón de diseño Facade que crea un fachada de acceso 
 * de esta forma no se accede directamente a los Dao desde los controladores
 * lo cual supone una buena práctica.
 */
public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findOne(Long id);
	
	public Usuario findByUsername(String name);
	public Usuario findByEmail(String email); 
	
	public void delete(Long id);
	
	public void save(Usuario user);
	
	void actualizarRol(Usuario usuario, RoleEnum nuevoRol);

	public boolean usuarioTieneRol(Long userId, RoleEnum rolSolicitado);

	public void actualizarRol(Long userId, RoleEnum nuevoRol) throws AccountNotFoundException;

	public Usuario esUsuarioCoordinador(Long articuloId, Authentication authentication);
	public Usuario esUsuarioCoordinadorPorWiki(Long wikiId, Authentication authentication);
	
	public void deleteByUsuario(Usuario usuario);

}
