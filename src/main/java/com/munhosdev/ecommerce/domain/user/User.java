package com.munhosdev.ecommerce.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String cpf;
    private String number;

    @Field("role")
    private String role;



    public User (UserDTO dto){
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.cpf = dto.cpf();
        this.number = dto.number();
        this.role = dto.role().toString();
    }


}
