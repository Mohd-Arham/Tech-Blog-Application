package com.arham.blog.Services;

import java.util.List;

import com.arham.blog.Payloads.CategoryDto;


public interface CategoryService {

	//create 
	
	 CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	
     CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	
	 void deleteCategory(Integer categoryId);
	
	//get
	
	 CategoryDto getCategory(Integer categoryId);
	
	//getAll
	
    List<CategoryDto> getCategory();
    
}
