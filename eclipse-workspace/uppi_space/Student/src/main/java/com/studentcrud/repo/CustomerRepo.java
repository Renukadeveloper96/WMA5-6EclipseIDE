package com.studentcrud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studentcrud.dto.OrderRequest;
import com.studentcrud.dto.OrderResponse;
import com.studentcrud.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer>{

	@Query("SELECT new com.studentcrud.dto.OrderResponse(c.name,c.email,c.gender,p.productName,p.price)FROM Customer c JOIN c.products p ")
	public List<OrderResponse>getJoinInformation();
}
