package com.backend.blog.service;

import com.backend.blog.entity.Category;
import com.backend.blog.exception.ResourceNotFoundException;
import com.backend.blog.payloads.CategoryDto;
import com.backend.blog.repositories.CategoryDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryDao categoryDao;

    @Override
     public CategoryDto addCategory(CategoryDto categoryDto) {
     Category category=this.dtoToCategory(categoryDto);
     Category category1=this.categoryDao.save(category);
     return this.categoryToDto(category1);
    }
    @Override
    public CategoryDto getCategoryById(long id) {
        Category category= this.categoryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Id is not found"));
        CategoryDto categoryDto=this.categoryToDto(category);
        categoryDto.setCategoryDescription(categoryDto.getCategoryTitle());
        return categoryDto;
    }
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category= this.categoryDao.findAll();
        List<CategoryDto> categoryDtos=category.stream().map(categorys->this.categoryToDto(categorys)).collect(Collectors.toList());
        return categoryDtos;
    }
    @Override
    public void deleteCategory(long id) {
        Category category= this.categoryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category id not found"));
          this.categoryDao.delete(category);

    }
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, long id) {
        Category category=this.categoryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Id is not found"));
             category.setCategoryTitle(categoryDto.getCategoryTitle());
             category.setCategoryDescription(categoryDto.getCategoryDescription());
             Category upCategory1=this.categoryDao.save(category);
           return this.categoryToDto(category);
    }
    private Category dtoToCategory(CategoryDto categoryDto){
      return this.modelMapper.map(categoryDto,Category.class);
    }
    private CategoryDto categoryToDto(Category category){
        return this.modelMapper.map(category,CategoryDto.class);
    }
}
