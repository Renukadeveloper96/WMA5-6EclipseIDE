package com.minton.userservice.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class ContactRequestDto {

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

}
