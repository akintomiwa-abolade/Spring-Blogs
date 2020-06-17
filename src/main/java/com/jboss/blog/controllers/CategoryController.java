package com.jboss.blog.controllers;

import com.jboss.blog.models.Category;
import com.jboss.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-category")
    public Category addCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }
    @GetMapping("/view-categories")
    public List<Category> viewCategories(){
        return categoryService.fetchCategories();
    }
    @GetMapping("/view-category/{id}")
    public Category viewCategory(@PathVariable Long id){
        return categoryService.fetchCategory(id);
    }
    @PutMapping("/update-category")
    public Category updateCategory(@RequestBody Category category){
        return categoryService.editCategory(category);
    }
    @DeleteMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.removeCategory(id);
    }
}
