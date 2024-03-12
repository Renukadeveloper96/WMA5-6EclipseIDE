package com.studentcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentcrud.dto.TravelPlanetDto;
import com.studentcrud.dto.TravelPlanetRequest;
import com.studentcrud.service.TravelPlanetService;

@RestController
public class TravelPlanetController {

	@Autowired
	private TravelPlanetService travelPlanetService;
	
	@PostMapping(value="/travelplanet")
	public ResponseEntity<?>createTravel(@RequestBody TravelPlanetRequest request){
		TravelPlanetDto dto=travelPlanetService.createPlanet(request);
		if(dto!=null) {
			return ResponseEntity.ok(travelPlanetService.createPlanet(request));
		}else {
			return ResponseEntity.badRequest().body("there is some problem in create travelPlanet");
		}
	}
	
	@GetMapping(value="/travelplanet")
	List<TravelPlanetDto>getAllTravel(){
		return  travelPlanetService.getAllTravel();
		}
	
	
	@GetMapping(value="/travelplanet/{id}")
	public TravelPlanetDto getbyTravel(@PathVariable("id")Integer id) {
		
		return travelPlanetService.getByTravelId(id);
	}
	
	@PutMapping(value="/travelplanet/{id}")
	public TravelPlanetDto update(@PathVariable("id")Integer id,@RequestBody TravelPlanetRequest request) {
		return travelPlanetService.update(id,request);
	}
	
	@DeleteMapping(value="/travelplanet/{id}")
	public String delete(@PathVariable("id")Integer id) {
		return travelPlanetService.deletebyid(id);
	}
	
}
