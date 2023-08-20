package com.proyectospring.app.models.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.models.dao.IUsuarioDao;
import com.proyectospring.app.models.entity.Usuario;

@Service
public class UsuarioServiceImpl  implements IUsuarioService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String name) {
		
		return usuarioDao.findByUsername(name);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		usuarioDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public void save(Usuario user) {
		
		usuarioDao.save(user);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		
		return (List<Usuario>) usuarioDao.findAll();
	}
	
	@Override
	@Transactional
	public void actualizarRol(Usuario usuario, RoleEnum nuevoRol) {
	    // Implementa la lógica para actualizar el rol del usuario usando SQL o mecanismos ORM
	    // Por ejemplo, puedes usar una consulta SQL nativa para actualizar el rol del usuario
	    String sqlActualizarRol = "UPDATE authorities SET authority = ? WHERE user_id = ?";
	    entityManager.createNativeQuery(sqlActualizarRol)
	        .setParameter(1, nuevoRol.name())
	        .setParameter(2, usuario.getId())
	        .executeUpdate();
	}


}
