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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PeticionRolController {

    @Autowired
    private PeticionRolService peticionRolService;
    
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/solicitar-rol")
    public String mostrarFormularioSolicitud(Model model) {
        model.addAttribute("roles", RoleEnum.values());
        return "solicitar-rol-form";
    }

    @PostMapping("/solicitar-rol")
    public String procesarSolicitudRol(
            @RequestParam("rolSolicitado") RoleEnum rolSolicitado,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            RedirectAttributes redirectAttributes
    ) {
        Long userId = customUserDetails.getUserId();
        
        // Implementa la lógica para verificar si el usuario ya tiene el rol solicitado
        if (usuarioService.usuarioTieneRol(userId, rolSolicitado)) {
            redirectAttributes.addFlashAttribute("error", "Ya tienes el rol solicitado");
            return "redirect:/perfil";
        }

        // Implementa la lógica para crear la solicitud de rol
        peticionRolService.lanzarSolicitudNuevoRol(userId, rolSolicitado);
        
        redirectAttributes.addFlashAttribute("success", "Solicitud de rol enviada con éxito");
        return "redirect:/perfil";
    }

 

    
    //mostrar las solicitudes de Rol
    @GetMapping("/gestor")
    public String gestionarUsuarios(Model model) {
        List<Usuario> listaUsuarios = usuarioService.findAll();
        List<PeticionRol> solicitudesRol = peticionRolService.obtenerTodasLasSolicitudes();

        model.addAttribute("listaUsuarios", listaUsuarios);
        model.addAttribute("predefinedRoles", RoleEnum.values());
        model.addAttribute("solicitudesRol", solicitudesRol);

        return "gesto_user";
    }
    
    /*
    //Rechazar peticion de Rol
    @PostMapping("/actualizar-rol")
    public String actualizarEstadoSolicitud(
            @RequestParam("userId") Long userId,
            @RequestParam("solicitudId") Long solicitudId,
            @RequestParam("action") String action
    ) {
        if (action.equals("reject")) {
            peticionRolService.actualizarEstadoSolicitud(solicitudId, PeticionStatus.RECHAZADA);
        }
        return "redirect:/gestor_user";
    }
    
    */
    
    @PostMapping("/actualizar-rol")
    public String actualizarRol(
            @RequestParam("userId") Long userId,
            @RequestParam("solicitudId") Long solicitudId,
            @RequestParam("action") String action
    ) throws AccountNotFoundException {
        PeticionRol solicitud = peticionRolService.obtenerPeticionPorId(solicitudId);

        if (action.equals("accept")) {
            // Actualiza el estado de la solicitud a ACEPTADA
            solicitud.setStatus(PeticionStatus.ACEPTADA);
            // Agrega el rol solicitado al usuario
            RoleEnum nuevoRol = solicitud.getRequestedAuthority();
            usuarioService.actualizarRol(userId, nuevoRol);
        } else if (action.equals("reject")) {
            // Actualiza el estado de la solicitud a RECHAZADA
            solicitud.setStatus(PeticionStatus.RECHAZADA);
        }

        peticionRolService.guardarSolicitud(solicitud);

        return "redirect:/gestor_user"; // Redirecciona de vuelta a la página de gestión
    }


}
