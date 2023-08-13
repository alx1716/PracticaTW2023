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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

/**
 * Esta va a ser la primera tabla donde va a ir todos los clientes,
 * En el caso de la práctica de TW serán USUARIOS. 
 */


@Entity
@Table(name="wikis")
public class Wiki  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@Column(name="Nombre")
	@NotEmpty  // Para validar que el campo no esté vacío. Sólo se utiliza con cadenas para el resto de objetos se usa @NotNull
	private String nombre;
	
	@Column(name="Apellido")
	@NotEmpty
	private String apellido;
	
	@Column(name="Email")
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@Column(name="Fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")  // este es el formato en que se deben meter las fechas en el formulario Spring convierte el String en formato fecha con este formato
	private Date createAt;
	
	
	//private String foto;  // archivo ue se va a subir a cada uno de los clientes. ESTE archivo es el que subiría a cada uno de los archivos o a las WIKIS?
	
	
	
	
	@OneToMany(mappedBy = "wiki",fetch=FetchType.LAZY, cascade= CascadeType.ALL) //una wiki (la clase en la que estamos), puede tener muchos artículos.// cascade es para que si a la wiki le hacemos algo se extienda a todos sus elementos hijos en cascada video 89 minuto 9:10
	private List<Articulo> articulos;  // una wiki va a tener muchos articulos.

	
	







	// Hay que crear los dos constructores uno sin y otro con los campos sin el ID al ser autonumerico?? así lo hace el de pildoras
	public Wiki() {
		
		articulos = new ArrayList<Articulo>(); // inicializamos la lista de facturas.
	}



	
	//getters y setters
	
	
	public Long getId() {
		return id;
	}

	/*public String getFoto() {
		return foto;
	}




	public void setFoto(String foto) {
		this.foto = foto;
	}*/




	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
     * método que añade una factura a la lista de facturas del cliente
     * 
     * @param factura
     */
	public void addArticulo(Articulo articulo) {  
		
		this.articulos.add(articulo);
	}




	

	
	/**
	 * método para poner el nombre completo del cliente en las facturas o donde sea que se necesite sólo poniendo el objeto cliente, directamente se llama a este método
	 * 
	 */
	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
	
	
	
	
	
	
}
