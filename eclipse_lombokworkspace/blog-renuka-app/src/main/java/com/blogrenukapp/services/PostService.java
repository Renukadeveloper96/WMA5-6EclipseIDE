package com.blogrenukapp.services;

import java.util.List;

import com.blogrenukapp.entity.Post;
import com.blogrenukapp.payload.PostDto;
import com.blogrenukapp.payload.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto,Long userId,Long categoryId);
	
	PostDto updatePost(PostDto postDto,Long postId);
	
	void deletePost(Long postId);
	
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize);
	
	PostDto getPostById(Long postId);
	
	List<PostDto>getPostByCategory(Long categoryId);
	
	public List<PostDto> getPostByUser(Long userId);
	
	List<Post>searchPost(String keyword);
}
