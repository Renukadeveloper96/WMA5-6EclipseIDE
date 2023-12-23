package com.minton.userservice.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

	public Role(String name)
	{
		this.name=name;
	}
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	//role name SuperAdmin,admin,manager etc
	@Column(unique = true, nullable = false)
	private String name;
	
	private String description;

	private boolean isActive;

	@CreationTimestamp
	@Column(updatable = false)
	private Date createdOn ;
	
	
	@UpdateTimestamp 
	private Date updatedOn ;

	public Role(String name, boolean b) {
		this.name=name;
		this.isActive=b;
	}
}