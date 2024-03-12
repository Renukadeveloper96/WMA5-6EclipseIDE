package com.studentcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentcrud.dto.CustomerRequest;
import com.studentcrud.dto.CustomerResponse;
import com.studentcrud.dto.OrderResponse;
import com.studentcrud.entity.Customer;
import com.studentcrud.repo.CustomerRepo;
import com.studentcrud.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@Autowired
	private CustomerRepo repo;
	@PostMapping(value="/placeOrders")
	public ResponseEntity<?> createCustomers(@RequestBody CustomerRequest request) {
		
		CustomerResponse dto=service.createCustomers(request);
		if(dto!=null) {
			return ResponseEntity.ok(service.createCustomers(request));
		}else {
			return ResponseEntity.badRequest().body("There is some issue in createCustomers");
		}
	}
	
	/*
	 * {
        "name": "santosh",
        "email": "s@gmail.com",
        "gender": "male",
        "products": [
            {
                "pid": 203,
                "productName": "watch",
                "qty": 2,
                "price": 5000
            },
             {
                "pid": 204,
                "productName": "cloths",
                "qty": 2,
                "price": 5000
            }
        ]
}

	 */
	@GetMapping(value="/getPlaceOrder")
	public List<CustomerResponse>getCustomer(){
		return service.getCustomer();
	}
	
	@GetMapping(value="/getPlaceOrderId/{id}")
	public CustomerResponse getPlaceOrderId(@PathVariable("id") Integer id) {
		return service.getPlaceOrderId(id);
	}
	
	@PutMapping(value="/getPlaceOrder/{id}")
	public CustomerResponse saveCustomer(@PathVariable("id") Integer id,@RequestBody CustomerRequest request) {
		return service.saveCustomer(id,request);
	}
	
	@DeleteMapping(value="/getPlaceOrder/{id}")
	public String deleteCustomerId(@PathVariable("id")Integer id) {
		return service.deleteCustomerId(id);
	}
	
	@GetMapping(value="/usingquery")
	public List<OrderResponse>getJoinInfo(){
		return repo.getJoinInformation();
	}
}
