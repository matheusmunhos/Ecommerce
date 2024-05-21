package com.munhosdev.ecommerce.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(){
        super("Categoria não encontrada");
    }
}
