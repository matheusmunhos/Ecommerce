package com.munhosdev.ecommerce.controller.user;

import com.munhosdev.ecommerce.domain.product.Product;
import com.munhosdev.ecommerce.domain.product.ProductDTO;
import com.munhosdev.ecommerce.domain.user.LoginUser;
import com.munhosdev.ecommerce.domain.user.User;
import com.munhosdev.ecommerce.domain.user.UserDTO;
import com.munhosdev.ecommerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> create (@RequestBody UserDTO dto){
        User newUser = this.service.create(dto);
        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> products = this.service.getAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id){
        User newUser = this.service.getById(id);
        return ResponseEntity.ok().body(newUser);
    }

    @DeleteMapping
    public void delete(@PathVariable String id){
        this.service.delete(id);
    }

    @GetMapping("/find/{cpf}")
    public ResponseEntity<User> getByCpf(@PathVariable String cpf){
        User newUser = this.service.findByCpf(cpf);
        return ResponseEntity.ok().body(newUser);
    }

    @PutMapping("/update/{cpf}")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO dto, String cpf){
        return ResponseEntity.ok().body(this.service.update(dto,cpf));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login (@RequestBody LoginUser user){
        return ResponseEntity.ok().body(this.service.login(user.email(), user.password()));
    }

}
