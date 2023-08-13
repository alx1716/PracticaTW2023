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


@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();  // para los menasajes de inicio de sesion con éxito usaremos un map para los mensajes
		
		FlashMap flashmap = new FlashMap();
		
		flashmap.put("success", "Hola "+authentication.getName()+" Haz iniciado sesion con éxito"); // con authentication se puede obtener el nombre y el rol o roles del usuario autenticado
		
		//ahora configuramos la salida del mensaje flash
		
		flashMapManager.saveOutputFlashMap(flashmap, request, response);
		
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
