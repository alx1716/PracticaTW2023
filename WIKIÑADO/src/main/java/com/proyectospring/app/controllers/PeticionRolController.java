package com.proyectospring.app.controllers;

import com.proyectospring.app.enums.PeticionStatus;
import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.models.entity.PeticionRol;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.service.CustomUserDetails;
import com.proyectospring.app.models.service.IUsuarioService;
import com.proyectospring.app.models.service.PeticionRolService;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 * Controlador para la gestión de peticiones de roles de usuario.
 * Administra las solicitudes y la gestión de roles de usuario en la aplicación.
 */
@Controller
public class PeticionRolController {

    @Autowired
    private PeticionRolService peticionRolService;
    
    @Autowired
    private IUsuarioService usuarioService;
    
    /**
     * Muestra el formulario para solicitar un nuevo rol de usuario.
     *
     * @param model El modelo para la vista.
     * @return La vista del formulario de solicitud de rol.
     */
    @GetMapping("/solicitar-rol")
    public String mostrarFormularioSolicitud(Model model) {
        model.addAttribute("roles", RoleEnum.values());
        return "solicitar-rol-form";
    }
    
    
    /**
     * Procesa la solicitud de un nuevo rol de usuario.
     *
     * @param rolSolicitado       El rol solicitado por el usuario.
     * @param customUserDetails   Los detalles personalizados del usuario autenticado.
     * @param redirectAttributes Atributos para redireccionamiento.
     * @return La vista de redireccionamiento después de procesar la solicitud.
     */
    @PostMapping("/solicitar-rol")
    public String procesarSolicitudRol(
            @RequestParam("rolSolicitado") RoleEnum rolSolicitado,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            RedirectAttributes redirectAttributes
    ) {
        Long userId = customUserDetails.getUserId();
        
      
        if (usuarioService.usuarioTieneRol(userId, rolSolicitado)) {
            redirectAttributes.addFlashAttribute("error", "Ya tienes el rol solicitado");
            return "redirect:/perfil";
        }

     // Verificar si el usuario tiene una solicitud pendiente para el mismo rol
        List<PeticionRol> solicitudesPendientes = peticionRolService.obtenerSolicitudesPendientesPorUsuario(userId);
        for (PeticionRol solicitud : solicitudesPendientes) {
            if (solicitud.getRequestedAuthority() == rolSolicitado) {
                redirectAttributes.addFlashAttribute("error", "Ya tienes una solicitud pendiente para este rol");
                return "redirect:/perfil";
            }
        }
        peticionRolService.lanzarSolicitudNuevoRol(userId, rolSolicitado);
        
        redirectAttributes.addFlashAttribute("success", "Solicitud de rol enviada con éxito");
        return "redirect:/perfil";
    }

 

    /**
     * Muestra la página de gestión de usuarios y solicitudes de roles.
     *
     * @param model El modelo para la vista.
     * @return La vista para gestionar usuarios y solicitudes de roles.
     */
    @GetMapping("/gestor")
    public String gestionarUsuarios(Model model) {
        List<Usuario> listaUsuarios = usuarioService.findAll();
        List<PeticionRol> solicitudesRol = peticionRolService.obtenerTodasLasSolicitudes();

        model.addAttribute("listaUsuarios", listaUsuarios);
        model.addAttribute("predefinedRoles", RoleEnum.values());
        model.addAttribute("solicitudesRol", solicitudesRol);

        return "gesto_user";
    }
    
    /**
     * Actualiza el rol de un usuario basado en una solicitud de rol.
     *
     * @param userId             El ID del usuario afectado.
     * @param solicitudId        El ID de la solicitud a procesar.
     * @param action             La acción a realizar (aceptar o rechazar).
     * @param redirectAttributes Atributos para redireccionamiento.
     * @return La vista de redireccionamiento después de actualizar el rol.
     * @throws AccountNotFoundException Si no se encuentra la cuenta de usuario.
     */
    @PostMapping("/actualizar-rol")
    public String actualizarRol(
            @RequestParam("userId") Long userId,
            @RequestParam("solicitudId") Long solicitudId,
            @RequestParam("action") String action,
            RedirectAttributes redirectAttributes
    ) throws AccountNotFoundException {
        PeticionRol solicitud = peticionRolService.obtenerPeticionPorId(solicitudId);

        if (action.equals("accept")) {
        	RoleEnum nuevoRol = solicitud.getRequestedAuthority();
            
            // Verifica si el usuario ya tiene asignado el rol
            if (!usuarioService.usuarioTieneRol(userId, nuevoRol)) {
                // Actualiza el estado de la solicitud a ACEPTADA
                solicitud.setStatus(PeticionStatus.ACEPTADA);
                // Agrega el rol solicitado al usuario
                usuarioService.actualizarRol(userId, nuevoRol);
                redirectAttributes.addFlashAttribute("success", "Actualización de rol realizada con éxito.");
            } else {
                redirectAttributes.addFlashAttribute("error", "El usuario ya tiene asignado el rol solicitado. Tienes que rechazar la solicitud.");
            }
            
        } else if (action.equals("reject")) {
            // Actualiza el estado de la solicitud a RECHAZADA
            solicitud.setStatus(PeticionStatus.RECHAZADA);
            redirectAttributes.addFlashAttribute("success", "Has rechazado la petición de Rol correctamente.");
           
        }

        peticionRolService.guardarSolicitud(solicitud);

        return "redirect:/gestor_user"; // Redirecciona de vuelta a la página de gestión
    }


}
