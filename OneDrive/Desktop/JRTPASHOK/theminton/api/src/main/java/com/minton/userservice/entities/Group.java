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
@Table(name = "groups_tbl")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String groupName;

	private Boolean isActive;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdOn;

	private Long createdBy;

	private Long modifiedBy;

	private LocalDateTime modifiedAt;

}