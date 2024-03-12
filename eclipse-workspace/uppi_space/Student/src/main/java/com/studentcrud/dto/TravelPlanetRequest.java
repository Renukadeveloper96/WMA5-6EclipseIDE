package com.studentcrud.dto;

import lombok.Data;

@Data
public class TravelPlanetRequest {
private Integer id;
	
	private String hotel;
	
	private String arrival;
	
	private String departure;
	
	private String type;
	
	private Integer guests;
	
	private Long price;
}
