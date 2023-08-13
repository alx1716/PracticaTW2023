package com.proyectospring.app.models.dao;




import org.springframework.data.repository.PagingAndSortingRepository;  //hereda de CRUD repository

import com.proyectospring.app.models.entity.Wiki;

public interface IWikiDao extends PagingAndSortingRepository<Wiki, Long> {  // los parámetros son la clase entity y el TIpo de la llave primaria de dicha clase que en este caso es de tipo Long
	
	
	/**
	 * Como se puede observar esta interfaz está vacía pero los métodos ya están implementados por detrás sólo hay que mirar la documentación que tengo en un 
	 * marcador del Firefox llamado SpringJPA.
	 * 
	 * 
	 * OJO. aunque en el cuerpo de la interfaz se pueden tener métodos con consultas o actualizaciones personalizadas.!!!!
	 * 
	 * 
	 * OJO y no está anotada ni con Component ni con Repository pero aún así se puede tratar como un Bean. ya que CRUDRepository si que es
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	/**
	 * Estos métodos se han eliminado en el vídeo 62 ya que vamos a utilizar la interfaz CRUD de Spring
	 */
	/*public List<Cliente> findAll();  // retorna una lista de clientes.
	
	public void save(Cliente cliente); // para guardar un cliente dentro de la tabla
	
	public Cliente findOne(Long id);  // para encontrar un cliente por su ID en la BBDD
	
	public void delete(Long id); // elimina un cliente de la BBDD*/

}
