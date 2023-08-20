package com.proyectospring.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import com.proyectospring.app.enums.PeticionStatus;
import com.proyectospring.app.enums.RoleEnum;
import com.proyectospring.app.models.dao.IRoleDao;
import com.proyectospring.app.models.dao.IUsuarioDao;
import com.proyectospring.app.models.entity.Role;
import com.proyectospring.app.models.entity.Usuario;
import com.proyectospring.app.models.service.CustomUserDetails;
import com.proyectospring.app.models.service.IUsuarioService;
import com.proyectospring.app.models.service.JpaUserDetailsService;

@Controller
public class PropuestaController {

    private final IRoleDao roleDao;
    private final IUsuarioService usuarioService;

    @Autowired
    public PropuestaController(IRoleDao roleDao, IUsuarioService usuarioService) {
        this.roleDao = roleDao;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/lanzar-propuesta")
    public String lanzarPropuesta(@RequestParam RoleEnum newRole, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        Usuario usuario = usuarioService.findOne(userId);
        Role role = roleDao.findByUsuario(usuario);

        if (role != null) {
            role.setNewRole(newRole);
            role.setStatus(PeticionStatus.PENDIENTE);
            roleDao.save(role);  
            model.addAttribute("exito", "Propuesta de cambio de rol lanzada y actualizada exitosamente.");
            model.addAttribute("error", null); // Limpiar el mensaje de error

        } else {
            // Si no existe un registro para este usuario, puedes crear uno nuevo
            // Mostrar mensaje de error en el perfil
            model.addAttribute("exito", null); // Limpiar el mensaje de éxito
            model.addAttribute("error", "No puedes lanzar una propuesta para un usuario que no tiene Rol.");
        
        }
        return "redirect:/lanzar-propuesta";
    }
    
    @GetMapping("/lanzar-propuesta") // Configura también para solicitudes GET
    public String mostrarResultado(Model model) {
   
        return "lanzar-propuesta"; // Vista para mostrar resultados
    }
  
}
