package com.munhosdev.ecommerce.controller.category;

import com.munhosdev.ecommerce.domain.category.Category;
import com.munhosdev.ecommerce.domain.category.CategoryDTO;
import com.munhosdev.ecommerce.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;



    @PostMapping("/create")
    public ResponseEntity<Category> create (@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(service.createCategory(categoryDTO));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findByCode/{code}")
    public ResponseEntity<Category> findByCode(@PathVariable("code") String code){
        return ResponseEntity.ok(service.findByCode(code));
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Void> delete (@PathVariable("code") String code){
        service.deleteCategory(code);
        return ResponseEntity.noContent().build();
    }


}
