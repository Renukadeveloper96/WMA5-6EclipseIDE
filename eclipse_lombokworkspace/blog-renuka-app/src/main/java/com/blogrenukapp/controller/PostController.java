package com.blogrenukapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogrenukapp.payload.ApiResponse;
import com.blogrenukapp.payload.CategoryDto;
import com.blogrenukapp.payload.PostDto;
import com.blogrenukapp.payload.PostResponse;
import com.blogrenukapp.services.PostService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Long userId,
			@PathVariable Long categoryId) {
		PostDto createPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Long userId) {
		List<PostDto> posts = postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/category")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Long categoryId) {
		List<PostDto> posts = postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<PostResponse> getPostAll(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
		PostResponse postResponse = postService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}

	@GetMapping("getPostById/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
		PostDto postDto = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted successfully", true), HttpStatus.OK);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto, @PathVariable("postId") Long postId) {
		PostDto updatedPost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
}
