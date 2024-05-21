package com.munhosdev.ecommerce.domain.client;

import com.munhosdev.ecommerce.domain.address.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "clientes")
public class Client {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String number;
    private Address address;

    public Client(ClientDTO dto){
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.cpf = dto.cpf();
        this.number = dto.number();
        this.address = new Address(dto.address());
    }


}
