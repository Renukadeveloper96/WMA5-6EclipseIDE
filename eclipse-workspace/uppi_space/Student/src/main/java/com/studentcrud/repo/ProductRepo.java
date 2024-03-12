package com.studentcrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentcrud.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>{

}
