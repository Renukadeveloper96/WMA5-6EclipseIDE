package com.blogrenukapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogrenukapp.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

}
