package com.proyectospring.app.models.service;

	import com.proyectospring.app.enums.PeticionStatus;
	import com.proyectospring.app.enums.RoleEnum;
	import com.proyectospring.app.models.entity.PeticionRol;
	import com.proyectospring.app.models.entity.Usuario;
	import com.proyectospring.app.models.dao.PeticionRolDao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	@Service
	public class PeticionRolService {

	    @Autowired
	    private PeticionRolDao peticionRolDao;
	    
	    @Autowired
	    private IUsuarioService usuarioService;

	  

		public void lanzarSolicitudNuevoRol(Long userId, RoleEnum rolSolicitado) {
			// Verifica si el usuario ya tiene el rol solicitado
	        Usuario usuario = usuarioService.findOne(userId);
	        if (usuario.tieneRol(rolSolicitado)) {
	            // Puedes lanzar una excepción personalizada aquí si lo prefieres
	            return; // El usuario ya tiene el rol, no es necesario enviar la solicitud
	        }

	        // Crea una nueva solicitud de rol
	        PeticionRol peticionRol = new PeticionRol();
	        peticionRol.setUsuario(usuario);
	        peticionRol.setRequestedAuthority(rolSolicitado);
	        peticionRol.setStatus(PeticionStatus.PENDIENTE); // El estado inicial es PENDIENTE

	        peticionRolDao.save(peticionRol);
	    }



		public List<PeticionRol> obtenerTodasLasSolicitudes() {
			
			 return (List<PeticionRol>) peticionRolDao.findAll();
		}



		@Transactional
		public void actualizarEstadoSolicitud(Long solicitudId, PeticionStatus nuevoEstado) {
		    PeticionRol peticionRol = peticionRolDao.findById(solicitudId).orElse(null);
		    if (peticionRol != null && peticionRol.getStatus() == PeticionStatus.PENDIENTE) {
		        peticionRol.setStatus(nuevoEstado);
		        peticionRolDao.save(peticionRol);
		    }
		}



		public PeticionRol obtenerPeticionPorId(Long solicitudId) {
			return peticionRolDao.findById(solicitudId).orElse(null);
		}



		public void guardarSolicitud(PeticionRol solicitud) {
			 peticionRolDao.save(solicitud);
			
		}


	}
