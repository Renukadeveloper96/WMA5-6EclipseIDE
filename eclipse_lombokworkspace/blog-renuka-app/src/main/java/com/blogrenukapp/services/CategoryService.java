package com.blogrenukapp.services;

import java.util.List;

import com.blogrenukapp.payload.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
	public void deleteCategory(Long categoryId);
	public CategoryDto getCategory(Long categoryId);
	public List<CategoryDto> getAllCategory();
	
}
