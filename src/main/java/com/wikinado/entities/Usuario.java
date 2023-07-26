package com.wikinado.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;




//Entidad que crea una tabla en la que se almacenan los usuarios, con las propiedades: id, nombre, password y rol para asociarlo a la tabla role
@Entity
@Table(name="usuarios")
public class Usuario {
	

	@Id //indicamos que es la clave primaria de la tabla
	//usamos el metodo de generación automatica
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = true, length = 64)
	private String nombre;
	@Column(unique = true, nullable = false, length = 64) //restricción para que no pueda haber emails repetidos
	private String email;
	@Column(nullable = false)
	private String password;
	
	
	
	
	
	//anotamos la cardinalidad, varios usuarios pueden pertenecer a un solo rol
	@ManyToMany(fetch = FetchType.EAGER)
	//permite generar la tabla usuario_role que casa cada usuario con un rol utilizando las claves foráneas de cada tabla
	@JoinTable(name="usuario_rol", joinColumns = @JoinColumn(name="usuario_id"), inverseJoinColumns =  @JoinColumn(name="rol_id"))
	//asocia un usuario a un rol determinado
	private Set<Rol> roles = new HashSet<Rol>();

	
	//constructor por defecto
	public Usuario() {}
		
		
	//constructor parametrizado de Usuario
	public Usuario(Long id, String email, String nombre, String password, Set<Rol> roles) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.roles = roles;
	}
	
	
	//getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
	 return "User: " + nombre + " <" + email + ">";
	 }

}
