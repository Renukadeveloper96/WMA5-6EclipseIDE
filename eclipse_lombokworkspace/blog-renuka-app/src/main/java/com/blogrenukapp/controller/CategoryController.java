package com.blogrenukapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogrenukapp.payload.ApiResponse;
import com.blogrenukapp.payload.CategoryDto;
import com.blogrenukapp.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDto>createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory=categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto>updatedCategory(@RequestBody CategoryDto categoryDto,@PathVariable("catId")Long catId){
		CategoryDto updatedCategory=categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Long catId){
		categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully", true),HttpStatus.OK);
	}
	
	@GetMapping("getCategoryById/{catId}")
	public ResponseEntity<CategoryDto>getCategoryById(@PathVariable Long catId){
		CategoryDto categoryDto=categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity <List<CategoryDto>>getCategoryAll(){
		List<CategoryDto> categories=categoryService.getAllCategory();
		return  ResponseEntity.ok(categories);
	}
	
}
