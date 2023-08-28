package com.proyectospring.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;


/**
 * Clase encargada de generar la información necesaria para renderizar la interfaz de paginación en la vista.
 *
 * @param <T> Tipo de elementos en la página.
 */
public class PageRender<T> {

	private String url;

	private Page<T> pagina;

	private int totalPaginas;

	private int numeroDeElementosPorPagina;

	private int paginaActual;

	private List<PageItem> paginas;
	
	/**
	 * Constructor de la clase paginador
	 * @param url
	 * @param pagina
	 */
	public PageRender(String url, Page<T> pagina) {

		this.url = url;
		this.pagina = pagina;
		this.paginas = new ArrayList<PageItem>();

		numeroDeElementosPorPagina = pagina.getSize();

		totalPaginas = pagina.getTotalPages();
		paginaActual = pagina.getNumber() + 1;

		int desde, hasta;
		// empezamos a dibujar el paginador en la vista
		if (totalPaginas <= numeroDeElementosPorPagina) {

			desde = 1;
			hasta = totalPaginas;

		} else {

			if (paginaActual <= numeroDeElementosPorPagina / 2) {

				desde = 1;
				hasta = numeroDeElementosPorPagina;
			} else if (paginaActual >= totalPaginas - numeroDeElementosPorPagina / 2) {

				desde = totalPaginas - numeroDeElementosPorPagina + 1;
				hasta = numeroDeElementosPorPagina;

			} else {
				desde = paginaActual - numeroDeElementosPorPagina / 2;
				hasta = numeroDeElementosPorPagina;
			}
		}

		for (int i = 0; i < hasta; i++) {

			paginas.add(new PageItem(desde + i, paginaActual == desde + i));
		}
	}

	// Getters y Setters
	
	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public boolean isFirst() {

		return pagina.isFirst();
	}

	public boolean isLast() {

		return pagina.isLast();
	}

	public boolean itHasNext() {

		return pagina.hasNext();
	}

	public boolean itHasPrevious() {

		return pagina.hasPrevious();
	}

}
