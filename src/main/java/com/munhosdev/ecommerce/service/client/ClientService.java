package com.munhosdev.ecommerce.service.client;

import com.munhosdev.ecommerce.domain.address.Address;
import com.munhosdev.ecommerce.domain.client.Client;
import com.munhosdev.ecommerce.domain.client.ClientDTO;
import com.munhosdev.ecommerce.exceptions.UserNotFoundException;
import com.munhosdev.ecommerce.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    @Autowired
    private final ClientRepository repository;


    public Client create(ClientDTO dto){
        Client client = new Client(dto);
        return repository.save(client);
    }

    public List<Client> getAll(){
        return repository.findAll();
    }

    public Client findByCpf(String cpf){
        Client client = repository.findByCpf(cpf);
        if(client == null){
            throw new UserNotFoundException();
        }
        return client;
    }

    public List<Client> findByFirstNameAndLastName(String firstName, String lastName) {
        Client client = repository.findByFirstNameAndLastName(firstName, lastName);
        if (client == null) {
            List<Client> similarClients = repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
            if (similarClients.isEmpty()) {
                similarClients = repository.findByFirstNameContainingIgnoreCase(firstName);
                if (similarClients.isEmpty()) {
                    throw new UserNotFoundException();
                } else {
                    return similarClients;
                }
            } else {
                return similarClients;
            }
        }

        return List.of(client);
    }

    public void deleteByCpf(String cpf){
        Client client = repository.findByCpf(cpf);
        if(client == null){
            throw new UserNotFoundException();
        }
        repository.delete(client);
    }

    public Client update(ClientDTO dto){
        Client client = this.repository.findByCpf(dto.cpf());
        if(client == null){
            throw new UserNotFoundException();
        }

        if(dto.firstName() != null){client.setFirstName(dto.firstName());}
        if(dto.lastName() != null){client.setLastName(dto.lastName());}
        if(dto.number() != null){client.setNumber(dto.number());}
        if (dto.address() != null) {
            client.setAddress(new Address(dto.address()));
        }
        return repository.save(client);

    }


}
