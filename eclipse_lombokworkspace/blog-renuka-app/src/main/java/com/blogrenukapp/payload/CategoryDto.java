package com.blogrenukapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
	private Long categoryId;
	
	@NotBlank
	@Size(min=4,message="Min size of category title is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10,message="Min size of category desc is 10")
	private String categoryDescription;
}
