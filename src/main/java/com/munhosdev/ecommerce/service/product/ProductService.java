package com.munhosdev.ecommerce.service.product;

import com.munhosdev.ecommerce.domain.category.Category;
import com.munhosdev.ecommerce.exceptions.CategoryNotFoundException;
import com.munhosdev.ecommerce.domain.product.Product;
import com.munhosdev.ecommerce.domain.product.ProductDTO;
import com.munhosdev.ecommerce.exceptions.ProductNotFoundException;
import com.munhosdev.ecommerce.repository.ProductRepository;
import com.munhosdev.ecommerce.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    public Product create(ProductDTO product){

        Category category = this.categoryService.findByCode(product.categoryCode());
        if(category == null){
            throw new CategoryNotFoundException();
        }
        Product newProduct = new Product(product);
        newProduct.setCategory(category);
        this.repository.save(newProduct);
        return newProduct;
    }

    public Product getById(String id){
        return this.repository.findById(id).orElseThrow();
    }

    public List<Product> getAll(){
        return this.repository.findAll();
    }

    public void delete(String id){
        this.repository.deleteById(id);
    }

    public Product findByCode(String code){
      return  this.repository.findByCode(code);
    }

    public Product update (ProductDTO dto, String code){
        Product updatedProduct = this.repository.findByCode(code);
        if (updatedProduct == null){
            throw new ProductNotFoundException();
        }

        if(!dto.name().isEmpty()) updatedProduct.setName(dto.name());
        if(!dto.description().isEmpty()) updatedProduct.setDescription(dto.description());
        if(!dto.value().toString().isEmpty()) updatedProduct.setValue(dto.value());
        if(!dto.code().isEmpty()) updatedProduct.setCode(dto.code());
        this.repository.save(updatedProduct);
        return updatedProduct;
    }





}
