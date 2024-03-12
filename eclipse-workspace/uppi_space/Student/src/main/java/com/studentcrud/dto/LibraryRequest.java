package com.studentcrud.dto;

import java.util.List;

import com.studentcrud.entity.Address;

import lombok.Data;

@Data
public class LibraryRequest {
	private Integer id;
	private String name;
	private Address address;
}
