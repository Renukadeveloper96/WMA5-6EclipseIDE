package com.studentcrud.dto;

import java.util.List;

import com.studentcrud.entity.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class CustomerResponse {
private int id;
	
	private String name;
	private String email;
	private String gender;
	private List<Product>products;
}
