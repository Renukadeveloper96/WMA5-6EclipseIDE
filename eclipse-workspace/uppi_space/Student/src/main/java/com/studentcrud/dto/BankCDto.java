package com.studentcrud.dto;

import lombok.Data;

@Data
public class BankCDto {
private Integer id;
	
	private Long bank_account;
	private String customer_name;
	private String gender;
}
