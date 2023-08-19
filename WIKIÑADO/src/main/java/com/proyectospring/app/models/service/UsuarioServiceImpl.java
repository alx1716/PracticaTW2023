package com.proyectospring.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectospring.app.models.dao.IUsuarioDao;
import com.proyectospring.app.models.entity.Usuario;

@Service
public class UsuarioServiceImpl  implements IUsuarioService{
	
	@Autowired
	private IUsuarioDao usuarioDao;

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
	
	

}
