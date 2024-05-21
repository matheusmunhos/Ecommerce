package com.munhosdev.ecommerce.repository;

import com.munhosdev.ecommerce.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

    Category findByCode(String code);
}
