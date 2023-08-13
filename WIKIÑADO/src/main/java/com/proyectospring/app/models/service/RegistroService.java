package com.proyectospring.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
/**
 * clase para iomplementar el patrón Fachada
 * para los registros de nuevos usuarios
 */
import org.springframework.transaction.annotation.Transactional;

import com.proyectospring.app.models.dao.IRoleDao;
import com.proyectospring.app.models.dao.IUsuarioDao;
import com.proyectospring.app.models.entity.Role;
import com.proyectospring.app.models.entity.Usuario;

@Service
public class RegistroService {

	// inyectamos el DAO
	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * Método que recibe un nuevo usuario y lo mete en la base de datos pero antes
	 * codifica el password y se le asigna un rol de Colaborador
	 * 
	 */
	@Transactional
	public void saveNuevoUsuario(Usuario nuevoUsuario) {

		// le añado el rol por defecto al usuario nuevo
		Role defaultRole = new Role();
		defaultRole.setAuthority("ROLE_COLABORADOR");
		roleDao.save(defaultRole); // guardo el nuevo rol en la BBDD para que tenga un ID válido
		
		nuevoUsuario.getRoles().add(defaultRole); // cada usuario tiene un ArrayList ya seteado por lo que sólo añado el nuevo rol
		nuevoUsuario.setEnabled(true);
		nuevoUsuario.setPassword(encoder.encode(nuevoUsuario.getPassword())); // aquí se encrypta la contraseña antes de
																				// que sea guardada en la BBDD
		usuarioDao.save(nuevoUsuario);

	}

	/**
	 * Creo que esto es todo para esta clase pero puiedo volver a retomar desde aquí
	 * si creo que debería de srevir para más que para guardar los nuevos usuarios
	 * en la BBDD Ya qué el primero rol se lo puedo dar en el controlador del
	 * registro y le puedo añadir más roles en otro controlador como el de Usuario?
	 * 
	 * De momento he creado un método booleano para saber si el Usuario ya se
	 * encuentra registrado en la BBDD+
	 */
	@Transactional(readOnly = true)
	public boolean comprobarUsuario(Usuario user) {
		if (usuarioDao.findByEmail(user.getEmail()) != null) {

			return true;
		}

		return false;
	}

}
