package com.proyectospring.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "propuestas")
public class PropuestaModificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  // toda entidad debe tener su id único

	private String estado;  // puede ser ACEPTADO, RECHAZADO, EN ESTUDIO
	@ManyToOne(fetch = FetchType.LAZY)
	private Articulo propuestaPara;  // este será el articulo al que está referenciando
	
	@ManyToOne(fetch = FetchType.LAZY) 
	private Usuario propuestoPor;   // este es el usuario que propone la modificación
	
}
