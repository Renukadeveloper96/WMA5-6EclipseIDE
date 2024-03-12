package com.studentcrud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="travelplanet")
@Data
public class TravelPlanet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String hotel;
	
	private String arrival;
	
	private String departure;
	
	private String type;
	
	private Integer guests;
	
	private Long price;
	
}
