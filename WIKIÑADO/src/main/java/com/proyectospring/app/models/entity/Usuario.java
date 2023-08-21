package com.proyectospring.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.proyectospring.app.enums.RoleEnum;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, unique = true)
	private String username;

	@Column(length = 60)
	private String password;
	@Column
	private String email;

	private Boolean enabled;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) // un usuario puede tener muchos roles.
	@JoinColumn(name = "user_id") // es la llave foranea que tiene la clase Role
	private List<Role> roles = new ArrayList<Role>();
	
	
	
	@OneToMany( mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // para las wikis asigandas al usuario.
	private List<UsuarioWiki> usuarioWikis = new ArrayList<>();
	
	@OneToMany( mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // para los artículos asigandos al usuario.
	private List<UsuarioArticulo> usuarioArticulos = new ArrayList<>();
	
	

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // un usuario tiene una lista de las modificaciones que ha realizado en caso de un COLABORADOR.
	private List<PropuestaModificacion> modificacionesPropuestas = new ArrayList<>();  // el SUPERVISOR deberá ver sólo las propuestas de los artículos que tiene asignados.
	
	//relacion con PeticionRol
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PeticionRol> peticionesRol = new ArrayList<>();
	
	
	//Getters and Setters
	
	
	public List<UsuarioArticulo> getUsuarioArticulos() {
		return usuarioArticulos;
	}



	public void setUsuarioArticulos(List<UsuarioArticulo> usuarioArticulos) {
		this.usuarioArticulos = usuarioArticulos;
	}
	
	public String getEmail() {
		return email;
	}
    
	

	public List<UsuarioWiki> getUsuarioWikis() {
		return usuarioWikis;
	}



	public void setUsuarioWikis(List<UsuarioWiki> usuarioWikis) {
		this.usuarioWikis = usuarioWikis;
	}



	public List<PropuestaModificacion> getModificacionesPropuestas() {
		return modificacionesPropuestas;
	}

	public void setModificacionesPropuestas(List<PropuestaModificacion> modificacionesPropuestas) {
		this.modificacionesPropuestas = modificacionesPropuestas;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public List<PeticionRol> getPeticionesRol() {
	    return peticionesRol;
	}

	public void setPeticionesRol(List<PeticionRol> peticionesRol) {
	    this.peticionesRol = peticionesRol;
	}
	
	public boolean tieneRol(RoleEnum rolSolicitado) {
		return roles.stream().anyMatch(r -> r.getAuthority() == rolSolicitado);
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
