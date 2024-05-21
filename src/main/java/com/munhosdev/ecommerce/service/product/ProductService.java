package com.munhosdev.ecommerce.service.product;

import com.munhosdev.ecommerce.domain.category.Category;
import com.munhosdev.ecommerce.exceptions.CategoryNotFoundException;
import com.munhosdev.ecommerce.domain.product.Product;
import com.munhosdev.ecommerce.domain.product.ProductDTO;
import com.munhosdev.ecommerce.exceptions.ProductAlredyExistsException;
import com.munhosdev.ecommerce.exceptions.ProductNotFoundException;
import com.munhosdev.ecommerce.repository.ProductRepository;
import com.munhosdev.ecommerce.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        Product existingProduct = this.repository.findByCode(product.code());
        if(existingProduct != null){
            throw new ProductAlredyExistsException("Produto com o código: " + product.code() + " já existe.");
        }

        BigDecimal valorOriginal = product.value();
        BigDecimal valorFinal = calcularValorComPorcentagem(valorOriginal, product.porcentagem());

        Product newProduct = new Product(product);
        newProduct.setCategory(category);
        newProduct.setValue(valorFinal);
        newProduct.setOriginalValue(valorOriginal);
        newProduct.setPorcentagem(product.porcentagem());

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

        updatedProduct.setPorcentagem(dto.porcentagem());
        updatedProduct.setOriginalValue(dto.value());

        if (dto.categoryCode() != null){
            updatedProduct.setCategory(this.categoryService.findByCode(updatedProduct.getCategory().getCode()));
        }

        BigDecimal valorFinal = calcularValorComPorcentagem(dto.value(),dto.porcentagem());
        updatedProduct.setValue(valorFinal);
        this.repository.save(updatedProduct);
        return updatedProduct;
    }

    public void deleteall(){
        repository.deleteAll();
    }

    public BigDecimal calcularValorComPorcentagem(BigDecimal valorOriginal, int porcentagem) {
        BigDecimal percentual = BigDecimal.valueOf(porcentagem).divide(BigDecimal.valueOf(100));
        BigDecimal valorAdicional = valorOriginal.multiply(percentual).setScale(2, RoundingMode.HALF_UP);
        return valorOriginal.add(valorAdicional);
    }

    public Product repor(String code, int quantity){
        Product newProduct = this.repository.findByCode(code);
        if(newProduct == null){
            throw new ProductNotFoundException();
        }
        int originalQuantity = newProduct.getQuantity();
        int newQuantity = originalQuantity + quantity;
        newProduct.setQuantity(newQuantity);
        return this.repository.save(newProduct);
    }


}
