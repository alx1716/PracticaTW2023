package com.proyectospring.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectospring.app.models.dao.IUsuarioDao;
import com.proyectospring.app.models.dao.IUsuarioWikiDao;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioWiki;
import com.proyectospring.app.models.entity.Wiki;
@Service
public class UsuarioWikiServiceImpl implements IUsuarioWikiService {
	
	@Autowired
	private IUsuarioWikiDao usuarioWikiDao;

	@Override
	public List<UsuarioWiki> findAllByUsuarioId(Long id) {
		
		return usuarioWikiDao.findALLByUsuarioId(id);
	}

	@Override
	public void save(UsuarioWiki wikiAsignada) {
		
		usuarioWikiDao.save(wikiAsignada);
		
	}

	@Override
	public UsuarioWiki findOneById(Long id) {
		
		return usuarioWikiDao.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		
		usuarioWikiDao.deleteById(id);
		
	}

	@Override
	public UsuarioWiki findByUsuarioAndWiki(Usuario user, Wiki wiki) {
		
		return usuarioWikiDao.findByUsuarioAndWiki(user, wiki);
	}
	
	

}
