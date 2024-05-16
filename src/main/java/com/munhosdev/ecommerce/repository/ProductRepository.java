package com.munhosdev.ecommerce.repository;

import com.munhosdev.ecommerce.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository <Product, String> {

    Product findByCode(String code);

}
