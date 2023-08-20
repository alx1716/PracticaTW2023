package com.proyectospring.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Esta clase va a ser una relación intermedia para manejar la asignación de los
 * usuarios a las wikis
 * 
 */
@Entity
@Table(name = "usuarios_wikis" , uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "wiki_id"})})
public class UsuarioWiki implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "wiki_id")
	private Wiki wiki;
	
	

	// Getters and setters...

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Wiki getWiki() {
		return wiki;
	}

	public void setWiki(Wiki wiki) {
		this.wiki = wiki;
	}

	private static final long serialVersionUID = 1L;
}
