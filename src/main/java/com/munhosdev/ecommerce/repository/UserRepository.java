package com.munhosdev.ecommerce.repository;

import com.munhosdev.ecommerce.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByCpf(String cpf);

    User findByEmailAndPassword(String email, String password);
}
