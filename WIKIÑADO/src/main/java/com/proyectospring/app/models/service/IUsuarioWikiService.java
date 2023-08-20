package com.proyectospring.app.models.service;

import java.util.List;

import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioWiki;
import com.proyectospring.app.models.entity.Wiki;

public interface IUsuarioWikiService {
	
	public List<UsuarioWiki> findAllByUsuarioId(Long id); // para encontrar sólo los registros que estén relacionados a ese usuario
	
	public void save( UsuarioWiki wikiAsignada);
	
	public UsuarioWiki findOneById(Long id);
	
	public void deleteById(Long id);
	
	public UsuarioWiki findByUsuarioAndWiki(Usuario user , Wiki wiki); 

}
