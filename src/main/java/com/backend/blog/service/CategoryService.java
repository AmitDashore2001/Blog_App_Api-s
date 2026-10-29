package com.backend.blog.service;

import com.backend.blog.entity.Category;
import com.backend.blog.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public CategoryDto addCategory(CategoryDto categoryDto);

    public CategoryDto getCategoryById(long id);

    public List<CategoryDto> getAllCategory();

    public void deleteCategory(long id);

    public CategoryDto updateCategory(CategoryDto categoryDto, long id);

}
