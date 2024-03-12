package com.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentcrud.dto.CustomerRequest;
import com.studentcrud.dto.CustomerResponse;
import com.studentcrud.entity.Customer;
import com.studentcrud.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo repo;
	
	@Override
	public CustomerResponse createCustomers(CustomerRequest request) {//request to entity;
		Customer customer =new Customer();
		customer.setEmail(request.getEmail());
		customer.setGender(request.getGender());
		customer.setName(request.getName());
		customer.setProducts(request.getProducts());
		repo.save(customer);
		
		CustomerResponse dto=new CustomerResponse();
		dto.setEmail(customer.getEmail());
		dto.setGender(customer.getEmail());
		dto.setName(customer.getName());
		dto.setProducts(customer.getProducts());
		dto.setId(customer.getId());
		return dto;
		
	}

	@Override
	public List<CustomerResponse> getCustomer() {
		List<Customer> entity=repo.findAll();
		return entity.stream().map(cr->{
			CustomerResponse dto=new CustomerResponse();
			dto.setEmail(cr.getEmail());
			dto.setGender(cr.getGender());
			dto.setName(cr.getName());
			dto.setId(cr.getId());
			dto.setProducts(cr.getProducts());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public CustomerResponse getPlaceOrderId(Integer id) {
	
		Customer customer=repo.findById(id).get();
		CustomerResponse dto=new CustomerResponse();
		dto.setEmail(customer.getEmail());
		dto.setGender(customer.getGender());
		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setProducts(customer.getProducts());
		return dto;
	}

	@Override
	public CustomerResponse saveCustomer(Integer id, CustomerRequest request) {
		Customer entity=repo.findById(id).get();
		entity.setEmail(request.getEmail());
		entity.setGender(request.getGender());
		entity.setName(request.getName());
		entity.setProducts(request.getProducts());
		repo.save(entity);
		
		CustomerResponse dto=new CustomerResponse();
		dto.setEmail(entity.getEmail());
		dto.setGender(entity.getGender());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setProducts(entity.getProducts());
		return dto;
	}

	@Override
	public String deleteCustomerId(Integer id) {
		Customer entity=repo.findById(id).get();
		repo.delete(entity);
		return "deleted";
	}

}
