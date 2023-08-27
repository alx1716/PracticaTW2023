package com.proyectospring.app.models.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.security.core.Authentication;

import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.models.entity.Usuario;


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
