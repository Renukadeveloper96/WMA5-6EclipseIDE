package com.studentcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentcrud.dto.OrderRequest;
import com.studentcrud.dto.OrderResponse;
import com.studentcrud.entity.Customer;
import com.studentcrud.repo.CustomerRepo;

@RestController
public class OrderController {

	@Autowired
	private CustomerRepo customerRepo;
	
	@PostMapping(value="/placeOrder")
	public Customer createCustomer(@RequestBody OrderRequest request) {
		return customerRepo.save(request.getCustomer());
	}
	
	@GetMapping(value="/getInfo")
	public List<OrderResponse>getJoinInformation(){
		return customerRepo.getJoinInformation();
		
	}
}
