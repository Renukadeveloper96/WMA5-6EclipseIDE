package com.minton.userservice.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "setting")
public class Setting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer noOfUsers;
	
	private String billingPlan;
	
	private Boolean isWhatsapp;
	
	private Boolean isTelegram;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;

	private Long modifiedBy;

	private LocalDateTime modifiedAt;

}