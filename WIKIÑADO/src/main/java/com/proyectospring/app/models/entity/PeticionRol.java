package com.proyectospring.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.proyectospring.app.enums.PeticionStatus;
import com.proyectospring.app.enums.RoleEnum;

/**
 * Clase para mapear las Peticiones de cambio de Roles con la BBDD
 */
@Entity
@Table(name = "peticiones_rol")
public class PeticionRol implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum requestedAuthority;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PeticionStatus status;

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

	public RoleEnum getRequestedAuthority() {
		return requestedAuthority;
	}

	public void setRequestedAuthority(RoleEnum requestedAuthority) {
		this.requestedAuthority = requestedAuthority;
	}

	public PeticionStatus getStatus() {
		return status;
	}

	public void setStatus(PeticionStatus status) {
		this.status = status;
	}
    
    

}