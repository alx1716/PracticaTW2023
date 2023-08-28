package com.proyectospring.app.excepctions;
/**
 * Para lanzar una excepcion en caso de que no exista una cuenta de usuario
 */
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}