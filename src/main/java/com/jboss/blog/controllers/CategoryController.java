package com.jboss.blog.controllers;

import com.jboss.blog.models.Category;
import com.jboss.blog.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api("General Operation pertaining to Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-category")
    @ApiOperation("Add new Category")
    public Category addCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping("/view-categories")
    @ApiOperation("Fetch existing Categories")
    public List<Category> viewCategories(){
        return categoryService.fetchCategories();
    }

    @GetMapping("/view-category/{id}")
    @ApiOperation("View a Single Category")
    public Category viewCategory(@PathVariable Long id){
        return categoryService.fetchCategory(id);
    }

    @PutMapping("/update-category")
    @ApiOperation("Update a Category")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category){
        return categoryService.editCategory(id, category);
    }

    @DeleteMapping("/delete-category/{id}")
    @ApiOperation("Delete a Category")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.removeCategory(id);
    }
}
