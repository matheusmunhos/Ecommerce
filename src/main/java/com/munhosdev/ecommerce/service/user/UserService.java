package com.munhosdev.ecommerce.service.user;

import com.munhosdev.ecommerce.domain.product.Product;
import com.munhosdev.ecommerce.domain.user.User;
import com.munhosdev.ecommerce.domain.user.UserDTO;
import com.munhosdev.ecommerce.exceptions.UserNotFoundException;
import com.munhosdev.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository repository;

    public User create(UserDTO dto){
        User newUser = new User(dto);
        this.repository.save(newUser);
        return newUser;
    }

    public User getById(String id){
        return this.repository.findById(id).orElseThrow();
    }

    public List<User> getAll(){
        return this.repository.findAll();
    }

    public void delete(String id){
        this.repository.deleteById(id);
    }

    public User findByCpf(String cpf){
        return  this.repository.findByCpf(cpf);
    }

    public User update(UserDTO dto, String cpf){

        User updateUser = this.findByCpf(cpf);
        if (updateUser == null){
            throw new UserNotFoundException();
        }
        if(!dto.email().isEmpty())  updateUser.setEmail(dto.email());
        if(!dto.name().isEmpty()) updateUser.setName(dto.name());
        if(!dto.number().isEmpty()) updateUser.setNumber(dto.number());
        if(!dto.password().isEmpty()) updateUser.setPassword(dto.password());
        if(!dto.role().toString().isEmpty()) updateUser.setRole(dto.role().toString());
        this.repository.save(updateUser);
        return updateUser;
    }

    public User login(String email,String password){
        User loggedUser = this.repository.findByEmailAndPassword(email,password);
        if(loggedUser == null){
            throw new UserNotFoundException();
        }
        return loggedUser;
    }


}
