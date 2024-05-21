package com.munhosdev.ecommerce.service.category;

import com.munhosdev.ecommerce.domain.category.Category;
import com.munhosdev.ecommerce.domain.category.CategoryDTO;
import com.munhosdev.ecommerce.exceptions.CategoryNotFoundException;
import com.munhosdev.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {


    @Autowired
    private final CategoryRepository categoryRepository;


    public Category createCategory(CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO);
        this.categoryRepository.save(category);
        return category;
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Category findById(String id) {
        return this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public Category findByCode(String code) {
        Category category = this.categoryRepository.findByCode(code);
        if (category == null){
            throw new CategoryNotFoundException();
        }
        return category;
    }






}
