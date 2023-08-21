package com.proyectospring.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.security.auth.login.AccountNotFoundException;

import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.excepctions.RoleAlreadyExistsException;
import com.proyectospring.app.models.dao.IUsuarioDao;
import com.proyectospring.app.models.entity.Role;
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
	    // Busca al usuario en la base de datos y actualiza sus roles
	    Usuario usuarioPersistente = usuarioDao.findById(usuario.getId()).orElse(null);
	    if (usuarioPersistente != null) {
	        List<Role> roles = usuarioPersistente.getRoles();
	        
	        // Verifica si el nuevo rol ya existe en los roles actuales del usuario
	        boolean nuevoRolExiste = roles.stream()
	                .anyMatch(role -> role.getAuthority() == nuevoRol);
	        
	        if (!nuevoRolExiste) {
	            // Si el nuevo rol no existe en los roles actuales, agrégalo
	            Role nuevoRole = new Role();
	            nuevoRole.setAuthority(nuevoRol);
	            nuevoRole.setUsuario(usuarioPersistente);
	            roles.add(nuevoRole);
	            
	            usuarioDao.save(usuarioPersistente);
	        }
	    }
	}


	@Override
	public boolean usuarioTieneRol(Long userId, RoleEnum rolSolicitado) {
		Usuario usuario = usuarioDao.findById(userId).orElse(null);
        if (usuario != null) {
            return usuario.tieneRol(rolSolicitado);
        }
		return false;
	}

	@Transactional
	public void actualizarRol(Long userId, RoleEnum nuevoRol) throws AccountNotFoundException, RoleAlreadyExistsException {
	    Optional<Usuario> optionalUsuario = usuarioDao.findById(userId);
	    if (optionalUsuario.isPresent()) {
	        Usuario usuario = optionalUsuario.get();
	        List<Role> roles = usuario.getRoles();

	        // Verificar si el nuevo rol ya existe en los roles del usuario
	        if (roles.stream().anyMatch(role -> role.getAuthority() == nuevoRol)) {
	            throw new RoleAlreadyExistsException("El usuario ya tiene el rol " + nuevoRol);
	        }

	        Role newRole = new Role(nuevoRol); // Crear el nuevo rol
	        roles.add(newRole); // Agregar el nuevo rol al usuario
	        usuarioDao.save(usuario);
	    } else {
	        throw new AccountNotFoundException("Usuario con ID " + userId + " no encontrado");
	    }
	}


}



