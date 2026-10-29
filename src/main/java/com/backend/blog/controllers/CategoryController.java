package com.backend.blog.controllers;

import com.backend.blog.payloads.CategoryDto;
import com.backend.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public CategoryDto categoryDto(@RequestBody CategoryDto categoryDto){
       return this.categoryService.addCategory(categoryDto);
    }
    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable long id){
        return this.categoryService.getCategoryById(id);
    }

    @GetMapping("/")
    public List<CategoryDto> getAllCategory(){
        return this.categoryService.getAllCategory();
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable long id){
        return this.categoryService.updateCategory(categoryDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id){
        this.categoryService.deleteCategory(id);
    }
}