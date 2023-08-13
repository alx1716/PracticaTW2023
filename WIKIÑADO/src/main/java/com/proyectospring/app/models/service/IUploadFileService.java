package com.proyectospring.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	public Resource load(String filename) throws MalformedURLException;
	
	public String copy(MultipartFile file) throws IOException;  // toma la imagen original y la copia al nuevo directorio para que podamos tomarla con el controlador
	
	public boolean delete( String filename); // para saber si se eliminó la foto y mandar el mensaje flash con el controlador
	
	
	public void deleteAll(); // para borrar el directorio uploads de forma que se cree uno nuevo en cada ejecución
	
	public void init() throws IOException;  // para crear nuevamente el directorio uploads

}
