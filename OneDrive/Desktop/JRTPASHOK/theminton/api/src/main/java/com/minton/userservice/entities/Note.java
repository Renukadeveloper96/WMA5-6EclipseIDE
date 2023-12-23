package com.minton.userservice.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String note;

	private String type;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;

	private Long modifiedBy;

	private LocalDateTime modifiedAt;
}