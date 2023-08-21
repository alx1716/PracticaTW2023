package com.proyectospring.app.models.entity;

import com.proyectospring.app.enums.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Role implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    // Getters and Setters

    public Role(RoleEnum nuevoRol) {
    	this.authority = nuevoRol;
	}

	public Role() {
		
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getAuthority() {
        return authority;
    }

    public void setAuthority(RoleEnum authority) {
        this.authority = authority;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
