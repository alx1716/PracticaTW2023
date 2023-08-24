package com.proyectospring.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectospring.app.models.dao.IUsuarioArticuloDao;
import com.proyectospring.app.models.dao.IUsuarioDao;
import com.proyectospring.app.models.dao.IUsuarioWikiDao;
import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.PropuestaModificacion;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.entity.UsuarioArticulo;
import com.proyectospring.app.models.entity.UsuarioWiki;
import com.proyectospring.app.models.entity.Wiki;
@Service
public class UsuarioArticuloServiceImpl implements IUsuarioArticuloService {
	
	@Autowired
	private IUsuarioArticuloDao usuarioArticuloDao;
	
	@Autowired
	private IPropuestaModificacionService porpuestaModificacionService;

	@Override
	public List<UsuarioArticulo> findAllByUsuarioId(Long id) {
		
		return usuarioArticuloDao.findALLByUsuarioId(id);
	}

	@Override
	public void save(UsuarioArticulo articuloAsignado) {
		
		usuarioArticuloDao.save(articuloAsignado);
		
	}

	@Override
	public UsuarioArticulo findOneById(Long id) {
		
		return usuarioArticuloDao.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		
		usuarioArticuloDao.deleteById(id);
		
	}

	@Override
	public UsuarioArticulo findByUsuarioAndArticulo(Usuario user, Articulo articulo) {
		
		return usuarioArticuloDao.findByUsuarioAndArticulo(user, articulo);
	}

	@Override
    public void delete(UsuarioArticulo relacion) {
        Articulo articulo = relacion.getArticulo();
        Usuario usuario = relacion.getUsuario();

        // Eliminar referencia al usuario en las propuestas asociadas al artículo
        for (PropuestaModificacion propuesta : articulo.getPropuestas()) {
            if (propuesta.getUsuario() != null && propuesta.getUsuario().equals(usuario)) {
                propuesta.setUsuario(null);
                porpuestaModificacionService.save(propuesta);
            }
        }

        // Eliminar relación de usuario-artículo
        articulo.getUsuarioArticulos().remove(relacion);
        usuario.getUsuarioArticulos().remove(relacion);

        usuarioArticuloDao.delete(relacion);
    }



}

	
	


