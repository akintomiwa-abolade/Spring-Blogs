package com.jboss.blog.services;

import com.jboss.blog.models.Category;
import com.jboss.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
    public List<Category> fetchCategories(){
        return categoryRepository.findAll();
    }
    public Category fetchCategory(Long id){
        return categoryRepository.findById(id).orElse(null);
    }
    public Category editCategory(Long id, Category category){
        Category defaultCats = categoryRepository.findById(id).orElse(null);
        defaultCats.setCatName(category.getCatName());
        defaultCats.setCatDesc(category.getCatDesc());
        return categoryRepository.save(defaultCats);
    }
    public String removeCategory(Long id){
        categoryRepository.deleteById(id);
        return "Category with ID " + id + "successfully deleted";
    }
}
