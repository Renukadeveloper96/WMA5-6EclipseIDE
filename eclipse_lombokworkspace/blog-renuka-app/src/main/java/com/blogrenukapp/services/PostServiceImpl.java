package com.blogrenukapp.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blogrenukapp.entity.Category;
import com.blogrenukapp.entity.Post;
import com.blogrenukapp.entity.User;
import com.blogrenukapp.exceptions.ResourceNotFoundException;
import com.blogrenukapp.payload.CategoryDto;
import com.blogrenukapp.payload.PostDto;
import com.blogrenukapp.payload.PostResponse;
import com.blogrenukapp.payload.UserDto;
import com.blogrenukapp.repositories.CategoryRepo;
import com.blogrenukapp.repositories.PostRepo;
import com.blogrenukapp.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Long userId,Long categoryId) {
		//		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));

		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("CategoryId", "id", categoryId));

		Post post=modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=postRepo.save(post);
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long postId) {
	
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		post.setAddedDate(new Date());
		post.setTitle(postDto.getTitle());
		post.setImageName(postDto.getImageName());
		post.setContent(postDto.getContent());
		Post updatedPost=postRepo.save(post);
		
//		PostDto postDto1=postToDto(updatedPost);or
//		return postDto1;
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Long postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize) {

	Pageable p=PageRequest.of(pageNumber, pageSize);
	Page<Post>pagePost=postRepo.findAll(p);
	List<Post>allPosts=pagePost.getContent();
	List<PostDto>postDtos=allPosts.stream().map(post->postToDto(post)).collect(Collectors.toList());
	
	PostResponse postResponse=new PostResponse();
	
	postResponse.setContent(postDtos);
	postResponse.setPageNumber(pagePost.getNumber());
	postResponse.setPageSize(pagePost.getSize());
	postResponse.setTotalElements(pagePost.getTotalElements());
	
	postResponse.setTotalPage(pagePost.getTotalPages());
	postResponse.setLastPage(pagePost.isLast());
	
	return postResponse;
	}

	@Override
	public PostDto getPostById(Long postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
//		return postToDto(post);or
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Long categoryId) {
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		List<Post>posts=postRepo.findByCategory(category);
		List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());	
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Long userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		List<Post>posts=postRepo.findByUser(user);
		List<PostDto>postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public PostDto postToDto(Post post) {
		PostDto postDto=modelMapper.map(post, PostDto.class);
		return postDto;
	}
	
	public Post DtoToPost(PostDto postDto) {
		Post post=modelMapper.map(postDto, Post.class);
		return post;
	}
}
