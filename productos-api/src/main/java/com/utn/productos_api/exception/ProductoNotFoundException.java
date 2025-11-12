package com.utn.productos_api.exception;

// Excepción personalizada para manejar casos donde un producto no es encontrado
public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(Long id) {
        super("No se encontró el producto con ID: " + id);
    }

    public ProductoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
