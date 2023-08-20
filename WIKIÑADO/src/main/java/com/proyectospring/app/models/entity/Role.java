package com.proyectospring.app.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.proyectospring.app.enums.PeticionStatus;
import com.proyectospring.app.enums.RoleEnum;


@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "authority"})}) // uniqueconstrains para crear un par indice unico en forma de llave
public class Role implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;
	
	private String authority;

	
	public Role(Long id, String authority) {
		this.id = id;
		this.authority = authority;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
    private RoleEnum newRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private PeticionStatus status;

  
	
	public Role() {
		
	}


	public RoleEnum getNewRole() {
		return newRole;
	}


	public void setNewRole(RoleEnum newRole) {
		this.newRole = newRole;
	}


	public PeticionStatus getStatus() {
		return status;
	}


	public void setStatus(PeticionStatus status) {
		this.status = status;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		
	}

}
