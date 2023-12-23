package com.minton.userservice.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ContactResponseDto {

	private Long id;

	private String firstName;

	private String lastName;

	private LocalDate dob;

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

	private List<GroupResponse> groupResponses;

	//private Long createdBy;

	private LocalDateTime createdAt;

	private Long modifiedBy;

	private LocalDateTime modifiedAt;

	private Boolean isActive;

}
