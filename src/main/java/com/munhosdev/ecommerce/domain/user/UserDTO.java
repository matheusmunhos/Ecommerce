package com.munhosdev.ecommerce.domain.user;

public record UserDTO(

        String name,
        String email,
        String password,
        String cpf,
        String number,
        UserEnum role


) {
}
