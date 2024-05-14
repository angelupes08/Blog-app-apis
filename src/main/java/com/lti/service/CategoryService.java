package com.lti.service;

import java.util.List;

import com.lti.payloads.CategoryDto;

public interface CategoryService {
	
	public void createCategory(CategoryDto cDto);
	
	public List<CategoryDto> getCategories();
	
	public CategoryDto getCategoryById(int id);
	
	public void deleteCategory(int id);
	
	public CategoryDto updateCategory(CategoryDto cDto,int id);

}
