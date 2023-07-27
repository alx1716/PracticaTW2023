package com.wikinado.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.wikinado.enums.TipoRol;



//Entidad que crea una tabla en la que se almacena el tipo de Rol y un id asociado al mismo
@Entity 
@Table(name="roles")
public class Rol {
	
	
	@Id  //indicamos que es la clave primaria de la tabla
	//usamos el metodo de generación automatica
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//no puede ser nulo
	@NonNull
	//permite almacenar el enumerado en la bbdd
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	//restricción para que un usuario no pueda tener un rol repetido
	private TipoRol tipoRol;

	//constructor por defecto
	public Rol() {}
		
	
	//constructor parametrizado
	public Rol(long id, TipoRol tipoRol) {
		this.id = id;
		this.tipoRol = tipoRol;
	}

	//getters y setters
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public TipoRol getTipoRol() {
		return tipoRol;
	}


	public void setTipoRol(TipoRol tipoRol) {
		this.tipoRol = tipoRol;
	}
	
}

