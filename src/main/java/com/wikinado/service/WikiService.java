package com.wikinado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wikinado.dao.WikiDao;
import com.wikinado.entities.Wiki;

@Service
public class WikiService {

	@Autowired
	private WikiDao wikiDao;
	

	//método que devuelve el listado de wikis
		public List<Wiki> wikiList(){
			return wikiDao.findAll();
		}
		
		//metodo que devuelve una wiki pasandole el nombre como argumento
		public Optional<Wiki> wikiNombre(String nombre){
			return wikiDao.findByNombre(nombre);
		}
		
		//metodo que devuelve una wiki pasandole el id como argumento
		public Optional<Wiki> wikiId(Integer id){
			return wikiDao.findById(id);
		}
			
		//metodo que almacena una Wiki en BBDD
		public void saveWiki(Wiki wiki){
			wikiDao.save(wiki);
		}
		
		//metodo que verifica si una wiki existe o no
		public boolean wikiExists(String nombre) {
			return wikiDao.existsByNombre(nombre);
		}
		
		//metodo que verifica si un id de wiki existe o no
		public boolean wikiExists(Integer id) {
			return wikiDao.existsById(id);
		}
		
		
	}
