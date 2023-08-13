package com.proyectospring.app.util.paginator;

public class PageItem {
	
	private int numeroPagina;
	
	private boolean actual;

	public PageItem(int numeroPagina, boolean actual) {
		
		this.numeroPagina = numeroPagina;
		this.actual = actual;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public boolean isActual() {
		return actual;
	}
	
	
	
	

}
