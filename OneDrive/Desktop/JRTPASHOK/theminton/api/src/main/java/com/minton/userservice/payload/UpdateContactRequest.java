package com.minton.userservice.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UpdateContactRequest {

	private LocalDate dob;

	private String city;

	private String state;

	private String country;

	private String courseName;

	private LocalDate courseDate;

	private String fieldOfInterest;

	private Long secondaryNumber;

	private String secondaryEmail;

	private String currentCompany;

	private String currentPosition;

	private String linkedInId;

	private String instagramId;

	private String faceBookId;

}
