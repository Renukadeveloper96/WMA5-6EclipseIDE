package com.studentcrud.service;

import java.util.List;

import com.studentcrud.dto.CustomerRequest;
import com.studentcrud.dto.CustomerResponse;
import com.studentcrud.entity.Customer;

public interface CustomerService {

	public CustomerResponse createCustomers(CustomerRequest request);

	public List<CustomerResponse> getCustomer();

	public CustomerResponse getPlaceOrderId(Integer id);

	public CustomerResponse saveCustomer(Integer id, CustomerRequest request);

	public String deleteCustomerId(Integer id);
}
