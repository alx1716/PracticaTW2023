package com.proyectospring.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.security.auth.login.AccountNotFoundException;

import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.excepctions.RoleAlreadyExistsException;
import com.proyectospring.app.models.dao.IArticuloDao;
import com.proyectospring.app.models.dao.IUsuarioDao;
import com.proyectospring.app.models.dao.IWikiDao;
import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.Role;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioWiki;
import com.proyectospring.app.models.entity.Wiki;

@Service
public class UsuarioServiceImpl  implements IUsuarioService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IArticuloDao articuloDao;
	
	@Autowired
	private IWikiDao wikiDao;
	
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
	
	
	@Transactional
	public void deleteByUsuario(Usuario usuario) {
		
		usuarioDao.delete(usuario);
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

	
	
	//método que valida si el usuario autenticado es el coordinador de la wiki del artículo que se pasa por argumento
	public Usuario esUsuarioCoordinador(Long articuloId, Authentication authentication) {
		 // Obtener el usuario autenticado
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	    
	    // Obtener el artículo a partir de su ID
	    Articulo articulo = articuloDao.findById(articuloId).orElse(null);
	    
	    if (articulo != null) {
	        // Obtener la wiki asociada al artículo
	        Wiki wiki = articulo.getWiki();
	        
	        if (wiki != null) {
	            // Obtener la lista de usuarios de la wiki
	            List<UsuarioWiki> usuarioWikis = wiki.getUsuarioWikis();
	            
	            for (UsuarioWiki usuarioWiki : usuarioWikis) {
	                // Verificar si el usuario tiene el rol de coordinador y su id coincide con el usuario autenticado
	                Usuario usuario = usuarioWiki.getUsuario();
	                if (usuario.tieneRol(RoleEnum.ROLE_COORDINADOR) && usuario.getId().equals(userDetails.getUserId())) {
	                    return usuario;
	                }
	            }
	        }
	    }
	    
	    return null;
	
	}
	
	//método que valida si el usuario autenticado es el coordinador de la wiki que se pasa por argumento
	public Usuario esUsuarioCoordinadorPorWiki(Long wikiId, Authentication authentication) {
	    // Obtener el usuario autenticado
	    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

	    // Obtener la wiki a partir de su ID
	    Wiki wiki = wikiDao.findById(wikiId).orElse(null);

	    if (wiki != null) {
	        // Obtener la lista de usuarios de la wiki
	        List<UsuarioWiki> usuarioWikis = wiki.getUsuarioWikis();

	        for (UsuarioWiki usuarioWiki : usuarioWikis) {
	            // Verificar si el usuario tiene el rol de coordinador y su id coincide con el usuario autenticado
	            Usuario usuario = usuarioWiki.getUsuario();
	            if (usuario.tieneRol(RoleEnum.ROLE_COORDINADOR) && usuario.getId().equals(userDetails.getUserId())) {
	                return usuario;
	            }
	        }
	    }

	    return null;
	}

	@Override
	public Usuario findByEmail(String email) {
		
		return usuarioDao.findByEmail(email);
	}

}









