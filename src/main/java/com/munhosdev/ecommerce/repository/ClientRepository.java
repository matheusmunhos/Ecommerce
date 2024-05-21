package com.munhosdev.ecommerce.repository;

import com.munhosdev.ecommerce.domain.client.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository <Client, String> {

    Client findByCpf(String cpf);

    Client findByFirstNameAndLastName(String firstName, String lastName);
}
