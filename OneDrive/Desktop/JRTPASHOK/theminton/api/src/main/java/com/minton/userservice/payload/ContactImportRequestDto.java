package com.minton.userservice.payload;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class ContactImportRequestDto {

	@CsvBindByName(column = "First Name")
	@CsvBindByPosition(position = 0)
	@NotNull(message = "First Name is required")
	private String firstName;

	@CsvBindByName(column = "Last Name")
	@CsvBindByPosition(position = 1)
	@NotNull(message = "last Name is required")
	private String lastName;

	@CsvBindByName(column = "Date of Birth")
	@CsvBindByPosition(position = 2)
	private LocalDate dob;

	@CsvBindByName(column = "Primary Phone Number")
	@CsvBindByPosition(position = 3)
	private Long primaryNumber;

	@CsvBindByName(column = "Secondary Phone Number")
	@CsvBindByPosition(position = 4)
	private Long secondaryNumber;

	@CsvBindByName(column = "Primary Email ID")
	@CsvBindByPosition(position = 5)
	private String primaryEmail;

	@CsvBindByName(column = "Secondary Email ID")
	@CsvBindByPosition(position = 6)
	private String secondaryEmail;

	@CsvBindByName(column = "Course Name")
	@CsvBindByPosition(position = 7)
	private String courseName;

	@CsvBindByName(column = "Course Date")
	@CsvBindByPosition(position = 8)
	private LocalDate courseDate;

	@CsvBindByName(column = "Field of Interest")
	@CsvBindByPosition(position = 9)
	private String fieldOfInterest;

	@CsvBindByName(column = "City")
	@CsvBindByPosition(position = 10)
	@NotNull(message = "City is required")
	private String city;

	@CsvBindByName(column = "State")
	@CsvBindByPosition(position = 11)
	private String state;

	@CsvBindByName(column = "Country")
	@CsvBindByPosition(position = 12)
	private String country;

	@CsvBindByName(column = "Current Company")
	@CsvBindByPosition(position = 13)
	private String currentCompany;

	@CsvBindByName(column = "Current Position")
	@CsvBindByPosition(position = 14)
	private String currentPosition;

	@CsvBindByName(column = "LinkedIn ID")
	@CsvBindByPosition(position = 15)
	private String linkedInId;

	@CsvBindByName(column = "Instagram ID")
	@CsvBindByPosition(position = 16)
	private String instagramId;

	@CsvBindByName(column = "Facebook ID")
	@CsvBindByPosition(position = 17)
	private String faceBookId;

}
