package com.proyectospring.app.util.paginator;
/**
 * Clase que representa un elemento de página en la paginación.
 */
public class PageItem {
	
	private int numeroPagina;
	
	private boolean actual;
	
	/**
     * Constructor para crear un nuevo elemento de página.
     *
     * @param numeroPagina El número de la página.
     * @param actual Indica si este elemento representa la página actual.
     */
	public PageItem(int numeroPagina, boolean actual) {
		
		this.numeroPagina = numeroPagina;
		this.actual = actual;
	}
	
	/**
     * Obtiene el número de la página.
     *
     * @return El número de la página.
     */
	public int getNumeroPagina() {
		return numeroPagina;
	}
	
	/**
     * Verifica si este elemento de página es la página actual.
     *
     * @return `true` si es la página actual, de lo contrario `false`.
     */
	public boolean isActual() {
		return actual;
	}
	
	
	
	

}
