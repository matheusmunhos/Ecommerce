package com.munhosdev.ecommerce.repository;

import com.munhosdev.ecommerce.domain.client.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientRepository extends MongoRepository <Client, String> {

    Client findByCpf(String cpf);

    Client findByFirstNameAndLastName(String firstName, String lastName);

    List<Client> findByFirstNameContainingIgnoreCase(String firstName);

    List<Client> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
}
