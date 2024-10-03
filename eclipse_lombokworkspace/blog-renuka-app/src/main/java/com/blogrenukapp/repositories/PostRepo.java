package com.blogrenukapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogrenukapp.entity.Category;
import com.blogrenukapp.entity.Post;
import com.blogrenukapp.entity.User;

public interface PostRepo extends JpaRepository<Post, Long>{

	List<Post>findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
}
