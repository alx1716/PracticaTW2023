package com.proyectospring.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.proyectospring.app.models.entity.Articulo;

public interface IArticuloDao extends CrudRepository<Articulo, Long> {

}
