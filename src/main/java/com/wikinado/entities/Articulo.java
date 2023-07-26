package com.wikinado.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="articulos")
public class Articulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String contenido;
	
	//relacion 1 o muchos articulos a un usuario
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	//elemento de relación entre Wiki y articulo
	@ManyToOne(fetch = FetchType.LAZY)
	private Wiki wiki;
	
	@Column(name="fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	private Date fecha_creacion;
	
	public Articulo(int id, String titulo, String contenido, Date fecha_creacion, Wiki wiki, Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.contenido = contenido;
		this.fecha_creacion = fecha_creacion;
		this.wiki = wiki;
		this.usuario = usuario;
	}
	
	public Articulo() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Wiki getWiki() {
		return wiki;
	}

	public void setWiki(Wiki wiki) {
		this.wiki = wiki;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
