package com.proyectospring.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Clase para mapear las Wikis con la BBDD
 */
@Entity
@Table(name = "wikis")
public class Wiki implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Nombre")
	@NotEmpty // Para validar que el campo no esté vacío. Sólo se utiliza con cadenas para el
				// resto de objetos se usa @NotNull
	private String nombre;

	@Column(name = "Creador")
	@NotEmpty
	private String creador;

	@OneToMany(mappedBy = "wiki", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UsuarioWiki> usuarioWikis = new ArrayList<>();

	@Column(name = "Email")
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@Column(name = "Fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd") // este es el formato en que se deben meter las fechas en el formulario
											// Spring convierte el String en formato fecha con este formato
	private Date createAt;

	@OneToMany(mappedBy = "wiki", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // una wiki (la clase en la que
																						// estamos), puede tener muchos
																						// artículos.// cascade es para
																						// que si a la wiki le hacemos
																						// algo se extienda a todos sus
																						// elementos hijos en cascada
																						// video 89 minuto 9:10
	private List<Articulo> articulos; // una wiki va a tener muchos articulos.

	public Wiki() {

		articulos = new ArrayList<Articulo>(); // inicializamos la lista de artículos.
	}

	// getters y setters

	public Long getId() {
		return id;
	}

	public List<UsuarioWiki> getUsuarioWikis() {
		return usuarioWikis;
	}

	public void setUsuarioWikis(List<UsuarioWiki> usuarioWikis) {
		this.usuarioWikis = usuarioWikis;
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

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	/**
	 * método que añade un artículo a la lista de artículos de la wiki
	 * 
	 * @param articulo
	 */
	public void addArticulo(Articulo articulo) {

		this.articulos.add(articulo);
	}

	/**
	 * método para poner el nombre completo del usuario en los artículos o donde sea
	 * que se necesite sólo poniendo el objeto wiki, directamente se llama a este
	 * método
	 * 
	 */
	@Override
	public String toString() {
		return nombre + " " + creador;
	}

}
