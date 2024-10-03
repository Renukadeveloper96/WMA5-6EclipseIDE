package com.blogrenukapp.payload;

import java.util.Date;

import lombok.Data;

@Data
public class PostDto {

	private Long postId;
	
	private String title;

	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;

}
