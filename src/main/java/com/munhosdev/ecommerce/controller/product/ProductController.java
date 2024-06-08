package com.munhosdev.ecommerce.controller.product;

import com.munhosdev.ecommerce.domain.product.Product;
import com.munhosdev.ecommerce.domain.product.ProductDTO;
import com.munhosdev.ecommerce.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService service;


    @PostMapping("/create")
    public ResponseEntity<Product> create (@RequestBody ProductDTO dto){
        Product newProduct = this.service.create(dto);
        return ResponseEntity.ok().body(newProduct);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = this.service.getAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id){
        Product newProduct = this.service.getById(id);
        return ResponseEntity.ok().body(newProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.service.delete(id);
    }

    @GetMapping("/find/{code}")
    public ResponseEntity<Product> getByCode(@PathVariable String code){
        Product newProduct = this.service.findByCode(code);
        return ResponseEntity.ok().body(newProduct);
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<Product> update(@RequestBody ProductDTO dto, @PathVariable String code){
        return ResponseEntity.ok().body(this.service.update(dto,code));
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        service.deleteall();
    }

    @PutMapping("/repor/{codigo}/{quantity}")
    public ResponseEntity<Product> repor(@PathVariable String codigo, @PathVariable int quantity){
        return ResponseEntity.ok().body(this.service.repor(codigo,quantity));
    }



}
