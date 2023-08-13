package com.proyectospring.app.models.entity;

/**
 * OJO las facturas serán los articulos
 * así una wiki tendrá varios articulos
 */



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="articulos") //los nombres de las tablas van en plural.
public class Articulo implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String titulo;
	@Column(columnDefinition = "TEXT") // esto lo hago para poder insertar textos largos en las consultas sql en este campo.
	private String contenido;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	
	private Date fechaCreacion;
	
	@ManyToOne(fetch= FetchType.LAZY)  //muchos articulos a una wiki // el fetch LAZY es el recomendado.Video 89 minuto 7:40
	private Wiki wiki;
	
	
	@ManyToOne(fetch= FetchType.LAZY)
    private Usuario usuarioAsignado;          // esto es para ver el usuario al que está asignado el artículo
	
	@OneToMany(mappedBy = "propuestaPara", cascade = CascadeType.ALL)
	private List<PropuestaModificacion> propuestas;
	
	
	
	
	


	/**
	 * método para que antes de la persistencia de esta factura se le asigne una fecha de creación
	 */
	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}
	
	
	//getters and setters
	
	
	
	public Long getId() {
		return id;
	}
	public List<PropuestaModificacion> getPropuestas() {
		return propuestas;
	}


	public void setPropuestas(List<PropuestaModificacion> propuestas) {
		this.propuestas = propuestas;
	}


	public Usuario getUsuarioAsignado() {
		return usuarioAsignado;
	}


	public void setUsuarioAsignado(Usuario usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}


	public void setId(Long id) {
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
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Wiki getWiki() {
		return wiki;
	}
	public void setWiki(Wiki wiki) {
		this.wiki = wiki;
	}
	





	private static final long serialVersionUID = 1L;  // todas las entidades que implementan serializable deben tener este atributo.
}
