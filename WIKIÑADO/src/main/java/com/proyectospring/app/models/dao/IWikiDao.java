package com.proyectospring.app.models.dao;




import org.springframework.data.repository.PagingAndSortingRepository;  //hereda de CRUD repository

import com.proyectospring.app.models.entity.Wiki;

/**
 * interfaz de acceso a los datos
 */
public interface IWikiDao extends PagingAndSortingRepository<Wiki, Long> {  // los parámetros son la clase entity y el TIpo de la llave primaria de dicha clase que en este caso es de tipo Long
	


}
