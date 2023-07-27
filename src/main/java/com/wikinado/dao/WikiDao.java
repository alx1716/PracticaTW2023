package com.wikinado.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.wikinado.entities.Wiki;

public interface WikiDao extends JpaRepository<Wiki, Integer> {

	//devuelve una wiki en funcion de su nombre
	//primero se busca
	Optional<Wiki> findByNombre(String nombre);
	//se devuelve booleano como resultado si lo ha encontrado o no
	boolean existsByNombre(String nombre);
}
