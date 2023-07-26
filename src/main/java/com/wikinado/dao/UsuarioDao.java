//DAO para la entidad Usuario
package com.wikinado.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wikinado.entities.Usuario;


//hereda de JpaRepository a la que le pasa la entidad y el tipo de dato de la clave primaria
@Repository
public interface UsuarioDao  extends JpaRepository<Usuario, Long>{
	
	//devuelve un usuario en funcion de su email
	//primero se busca
	Optional<Usuario> findByEmail(String email);
	//se devuelve booleano como resultado si lo ha encontrado o no
	boolean existsByEmail(String email);
}
