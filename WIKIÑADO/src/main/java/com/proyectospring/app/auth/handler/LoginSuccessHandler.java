package com.proyectospring.app.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

/**
 * Manejador para el éxito de la autenticación de inicio de sesión.
 * Personaliza el comportamiento después de que un usuario haya iniciado sesión correctamente.
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	/**
     * Método llamado cuando la autenticación de inicio de sesión es exitosa.
     * Personaliza la respuesta y el comportamiento después de una autenticación exitosa.
     *
     * @param request        La solicitud HTTP.
     * @param response       La respuesta HTTP.
     * @param authentication La información de autenticación del usuario.
     * @throws IOException      Si ocurre un error de E/S al manejar la respuesta.
     * @throws ServletException Si ocurre un error en el servlet.
     */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
		
		// Se crea un administrador de mapas flash para los mensajes de inicio de sesión exitosos.
		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();  // para los menasajes de inicio de sesion con éxito usaremos un map para los mensajes
		
		FlashMap flashmap = new FlashMap();
		
		flashmap.put("success", "Hola "+authentication.getName()+" Haz iniciado sesion con éxito"); // con authentication se puede obtener el nombre y el rol o roles del usuario autenticado
		
		//ahora configuramos la salida del mensaje flash
		
		flashMapManager.saveOutputFlashMap(flashmap, request, response);
		
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
