package com.blogrenukapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogrenukapp.entity.Category;
import com.blogrenukapp.exceptions.ResourceNotFoundException;
import com.blogrenukapp.payload.CategoryDto;
import com.blogrenukapp.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	Category category=this.dtoToCategory(categoryDto);
	Category savedCategory=categoryRepo.save(category);
		return this.categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category updatedCategory=categoryRepo.save(category);
		CategoryDto categoryDto1=this.categoryToDto(updatedCategory);
		return categoryDto1;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Long categoryId) {
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categorys=categoryRepo.findAll();
		List<CategoryDto>categoryToDto=categorys.stream().map(category->this.categoryToDto(category)).collect(Collectors.toList());
		return categoryToDto;
	}
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category=modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	public CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto=modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
