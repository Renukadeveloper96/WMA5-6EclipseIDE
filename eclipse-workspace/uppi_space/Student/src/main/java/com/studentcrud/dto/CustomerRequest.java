package com.studentcrud.dto;

import java.util.List;

import com.studentcrud.entity.Customer;
import com.studentcrud.entity.Product;

import lombok.Data;

@Data
public class CustomerRequest {

private int id;
	
	private String name;
	private String email;
	private String gender;
	private List<Product>products;
}
