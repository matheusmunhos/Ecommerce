package com.munhosdev.ecommerce.exceptions;

public class ProductAlredyExistsException extends RuntimeException {

    public ProductAlredyExistsException() {
        super("Produto já existe.");
    }

    public ProductAlredyExistsException(String message) {
        super(message);
    }
}
