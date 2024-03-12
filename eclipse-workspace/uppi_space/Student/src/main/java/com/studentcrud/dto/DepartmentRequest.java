package com.studentcrud.dto;

import com.studentcrud.entity.DepartmentD;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class DepartmentRequest {
	private DepartmentD department;
}
