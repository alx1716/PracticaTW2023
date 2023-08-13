package com.proyectospring.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileServiceImplementation implements IUploadFileService {

	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path pathFoto = getPath(filename);
		Resource recurso = null;

		recurso = new UrlResource(pathFoto.toUri());
		if (!recurso.exists() || !recurso.isReadable()) {

			throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());

		}
		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); // identificador único
																									// del archivo.

		Path rootPath = getPath(uniqueFilename); // ruta relativa de la foto el resolve se encaraga de poner el slash

		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile(); // con esto ya podemos eliminar el archivo del directorio y que no ocupe
											// espacio

		// antes deliminar debemos validar
		if (archivo.exists() && archivo.canRead()) {
			if (archivo.delete()) {
				return true;

			}

		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
		
	}

	@Override
	public void init() throws IOException {
		
		Files.createDirectory(Paths.get(UPLOADS_FOLDER));
		
	}

}
