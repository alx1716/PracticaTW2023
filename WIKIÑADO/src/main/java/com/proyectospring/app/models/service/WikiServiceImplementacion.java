package com.proyectospring.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyectospring.app.models.dao.IWikiDao;
import com.proyectospring.app.models.dao.IArticuloDao;
import com.proyectospring.app.models.dao.IProductoDao;
import com.proyectospring.app.models.entity.Wiki;
import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.Producto;

/**
 * Una clase Service está básada en el patrón de diseño Facade también llamado Proxy.
 * 
 * Un único punto de accesso hacia distintos DAO o Repository
 * 
 * Dentro dew una clase Service se puede tener como atributo y operar con distintas clases DAO
 * y así evitamos tener que acceder de forma directa a los DAO's desde los controladores
 * 
 * Por cada método en la clase DAO vamos a tener un método en la clase service.
 * 
 * Todas las anotaciones Transactional del Dao se ponen en esta clase service.
 * 
 * 
 * Incluso dentro de un método dentro de la clase service podríamos interactuar con más de un DAO.
 */
@Service
public class WikiServiceImplementacion implements IWikiService {
	
	
	@Autowired
	private IWikiDao wikiDao;  // se inyecta la interfaz que hereda la CRUD de Spring
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	IArticuloDao articuloDao;

	@Override
	@Transactional(readOnly = true)  // al ser sólo una consulta y no una actualización insert.
	public List<Wiki> findAll() {
		
		return (List<Wiki>) wikiDao.findAll(); // tengo que hacer un cast a List porque el findAll devuelve un iterable.
	}

	@Override
	@Transactional // sin el readonly ya que es para insertar un nuevo registro
	public void save(Wiki wiki) {
		
		wikiDao.save(wiki);
	}

	@Override
	@Transactional(readOnly = true)  // al ser sólo una consulta y no una actualización insert.
	public Wiki findOne(Long id) {
		
		return wikiDao.findById(id).orElse(null);  // intenta encontrar el cliente por ID si no existe devuelve Null
	}

	@Override
	@Transactional
	public void delete(Long id) {
	
		wikiDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)  // al ser sólo una consulta y no una actualización insert.
	public Page<Wiki> findAll(Pageable pageable) {
		
		return wikiDao.findAll(pageable);
	}

	

	@Override
	@Transactional
	public void saveArticulo(Articulo articulo) {
		
		articuloDao.save(articulo);  // esta clase service es la que va a acceder al dao para que esta acceda a los datos
		
	}

	

	@Override
	@Transactional(readOnly = true)
	public Articulo findArticuloById(Long id) {
		
		return articuloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteArticulo(Long id) {
		
		articuloDao.deleteById(id);
	}

}
