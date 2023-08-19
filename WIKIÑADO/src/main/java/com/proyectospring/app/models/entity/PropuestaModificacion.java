package com.proyectospring.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "propuestas") 
public class PropuestaModificacion implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  // toda entidad debe tener su id único

	private String estado;  // puede ser ACEPTADO, RECHAZADO, EN ESTUDIO
	@ManyToOne(fetch = FetchType.LAZY)
	private Articulo articulo;  // este será el articulo al que está referenciando //OJO!!!  no creo que haga falta esto si ya el Artículo tiene una lista de las propuestas
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;   // este es el nombre del usuario que propone la modificación
	
	@Column(columnDefinition = "TEXT")
	private String propuesta;

	
	
	//Getters and Setters
	
	
	public Long getId() {
		return id;
	}

	public String getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(String contenidoPropuesta) {
		this.propuesta = contenidoPropuesta;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
