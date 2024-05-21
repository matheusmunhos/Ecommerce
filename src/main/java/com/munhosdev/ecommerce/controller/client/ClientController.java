package com.munhosdev.ecommerce.controller.client;

import com.munhosdev.ecommerce.domain.client.Client;
import com.munhosdev.ecommerce.domain.client.ClientDTO;
import com.munhosdev.ecommerce.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/client")
@RestController()
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping("/create")
    public ResponseEntity<Client> create(@RequestBody ClientDTO client) {
        return ResponseEntity.ok(service.create(client));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/findByCpf/{cpf}")
    public ResponseEntity<Client> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.findByCpf(cpf));
    }

    @GetMapping("/findByName/{name}/lastname/{lastname}")
    public ResponseEntity<Client> findByName(@PathVariable String name, @PathVariable String lastname) {
        return ResponseEntity.ok(service.findyByFirstNameAndLastName(name,lastname));
    }

    @PutMapping("/update")
    public ResponseEntity<Client> update(@RequestBody ClientDTO client) {
        return ResponseEntity.ok(service.update(client));
    }

    @DeleteMapping("{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        service.deleteByCpf(cpf);
       return ResponseEntity.noContent().build();
    }


}
