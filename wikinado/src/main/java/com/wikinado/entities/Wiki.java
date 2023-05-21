package com.wikinado.entities;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="wikis")
public class Wiki {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String nombre;
		private String descripcion;
		@Column(name="fecha_creacion")
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(iso=ISO.DATE)
		private Date fechaCreacion;
		
		//propiedad bidireccional para listar articulos de la wiki
		@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="wiki")
		private List<Articulo> articulos;
		
		public Wiki(int id, String nombre, String descripcion, Date fechaCreacion, List<Articulo> articulos) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.fechaCreacion = fechaCreacion;
			this.articulos = articulos;
		}
		
		public Wiki() {}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public Date getFechaCreacion() {
			return fechaCreacion;
		}

		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}

		public List<Articulo> getArticulos() {
			return articulos;
		}

		public void setArticulos(List<Articulo> articulos) {
			this.articulos = articulos;
		}
		
		public void addArticulos(Articulo articulo) {
			articulos.add(articulo);
		}
		
		
		
}
