package com.proyectospring.app.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectospring.app.models.entity.Wiki;
import com.proyectospring.app.models.entity.Articulo;
import com.proyectospring.app.models.entity.ItemFactura;
import com.proyectospring.app.models.entity.Producto;
import com.proyectospring.app.models.service.IWikiService;

//@Secured("ROLE_GESTOR") // se anota la clase entera ya que TODOS los métodos deben ser sólo accesibles a los Gestores.
@Controller
@RequestMapping("/articulo")
@SessionAttributes("articulo")   // para que se mantenga el articulo en la sesion hasta que se persista en la BBDD
public class ArticuloController {
	
	@Autowired
	private IWikiService wikiService;
	
	
	/**
	 * método para ver el detalle de la factura de un cliente
	 */
	@GetMapping("/ver/{articuloId}")
	public String verDetalleArticulo(@PathVariable(value = "articuloId") Long articuloId, Model modelo, RedirectAttributes flash) throws IOException { // la Exception es por el url del archivo que vamos a buscar
		
		//obtenemos el articulo por su id
		Articulo articulo = wikiService.findArticuloById(articuloId);
		
		//miramos si el artículo está precargado.
		if (articulo.getContenido().startsWith("/")) { // para ver si la observacion comienza con una url
			
			
			/*
			 * ESTO VA A SER UN TEST PARA PODER CARGAR CONTENIDO DESDE LA URL DE LA OBSERVACION/CONTENIDO
			 * EN LA BBDD de los Articulos ya precargados. como he dicho esto es una prueba.
			 * 
			 * 
			 * 1 - obtengo la url del articulo
			 * 2 - lo configuro como un ClassPathResource
			 * 3 - pongo una variable de String para pasarle a la vista mediante el modelo. esto con la funcion: StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
			 * 
			 * */
			//1
			String urlArchivo= articulo.getContenido(); 
			//2
			ClassPathResource recurso = new ClassPathResource("static"+urlArchivo);
			//3
			articulo.setContenido(StreamUtils.copyToString(recurso.getInputStream(), StandardCharsets.UTF_8));  // aquí el articulo tendría el contenido ya cargado desde la url
			
			
			// tenía la intencion de guardarlo pero no creo que haga falta si antes se veía directamente
			
			wikiService.saveArticulo(articulo);  // esto guarda el artículo en la wiki, puede que esto sea lo que me da problemas???
			
			flash.addFlashAttribute("error", "El articulo se ha cargado desde una url ya puedes ir a verlo");
			//return "articulo/ver"; // aquí lo estoy retornando sin haberlo pasado al modelo, por eso al haberse guardado ya en la BBDD ahí si me lo carga en la puta vista!!!
			
			
		}
		
		
		
		
		modelo.addAttribute("articulo", articulo);  // se pasa el articulo a la vista
		modelo.addAttribute("titulo", "Estás opinando sobre: "+ articulo.getTitulo());
		
		return "articulo/ver";
	}
	
	@GetMapping("/formulario/{wikiId}")
	public String crear(@PathVariable(value = "wikiId") Long wikiId,  Model modelo, RedirectAttributes flash) {
		
		Wiki wiki = wikiService.findOne(wikiId);
		
		if (wiki == null) { // nos aseguramos de que el cliente exista
			
			flash.addFlashAttribute("error", "La Wiki no existe en la BBDD");
			return "redirect:/listar";
		}
		
		Articulo articulo = new Articulo(); // creamos el articulo
		articulo.setWiki(wiki);    // un articulo debe ser para una wiki.
		modelo.addAttribute("articulo", articulo); // se le pasa el articulo a la vista
		modelo.addAttribute("titulo", "Formulario de Articulo");
		return "articulo/formulario"; // devolvemos la vista
	}
	
	

	
	/**
	 * vamos a crera el método para guardar la factura en la BBDD
	 * 
	 */
	@PostMapping("/formulario")
	public String guardarArticulo( @Valid Articulo articulo,BindingResult resultado, Model modelo, @RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash, SessionStatus status ) {
		
		if (resultado.hasErrors()) {
			modelo.addAttribute("titulo", "Crear Articulo");
			return "articulo/formulario";
		}
		
	
		
		wikiService.saveArticulo(articulo); //aquí se guarda la factura con todas sus líneas y sus observaciones que vienen del formulario desde la BBDD esto me va a servir para implementar el registro de los articulos
		
		status.setComplete();
		
		flash.addFlashAttribute("success", "Articulo creado con éxito!");
		return "redirect:/ver/"+ articulo.getWiki().getId();
		
	}
	
	/**
	 * implementamos el método para borrar facturas de un cliente
	 */
	@GetMapping("/eliminar/{id}")  //ojo porque este mapping es para mapear el método para que pueda ser invocado desde la vista!!
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		Articulo articulo = wikiService.findArticuloById(id);
		
		if (articulo != null) {
			
			wikiService.deleteArticulo(id);
			flash.addFlashAttribute("success", "Articulo Eliminado con Éxito!!");
			return "redirect:/ver/"+articulo.getWiki().getId();
			
		}
		
		flash.addFlashAttribute("error", "El Articulo no existe en la BBDD , no ha podido ser eliminado");
		return"redirect:/listar";
	}
		
}
