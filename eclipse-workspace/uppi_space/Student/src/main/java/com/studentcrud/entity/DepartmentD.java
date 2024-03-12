package com.studentcrud.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentD {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
	
	@OneToMany(targetEntity=EmployeeB.class,cascade=CascadeType.ALL)
	@JoinColumn(name="de_fk",referencedColumnName="id")
	private List<EmployeeB> employee;
}
