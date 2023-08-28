package com.proyectospring.app.excepctions;
/**
 * Para lanzar una excepción en caso de que ya exista ese rol
 */
public class RoleAlreadyExistsException extends RuntimeException {
    public RoleAlreadyExistsException(String message) {
        super(message);
    }
}