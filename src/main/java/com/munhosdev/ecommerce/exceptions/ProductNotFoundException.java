package com.munhosdev.ecommerce.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(){
        super("Produto não encontrado");
    }
}
