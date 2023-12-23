package com.minton.userservice.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "city", nullable = false)
	private String city;

	private String state;

	private String country;

	private String courseName;

	private LocalDate courseDate;

	private String fieldOfInterest;

	private Long primaryNumber;

	private Long secondaryNumber;

	private String primaryEmail;

	private String secondaryEmail;

	private String currentCompany;

	private String currentPosition;

	private String linkedInId;

	private String instagramId;

	private String faceBookId;

	private LocalDateTime createdAt;

	private Long modifiedBy;

	private LocalDateTime modifiedAt;

//	@ManyToMany(mappedBy = "contacts")
//    private List<Group> groups = new ArrayList<>();

	@Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
	private Boolean isActive = true;

}
