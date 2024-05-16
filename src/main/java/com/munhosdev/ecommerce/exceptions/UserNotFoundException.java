package com.munhosdev.ecommerce.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("Usuario não encontrado");
    }
}
