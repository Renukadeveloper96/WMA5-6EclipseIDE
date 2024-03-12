package com.studentcrud.service;

import java.util.List;

import com.studentcrud.dto.TravelPlanetDto;
import com.studentcrud.dto.TravelPlanetRequest;

public interface TravelPlanetService {

	public TravelPlanetDto createPlanet(TravelPlanetRequest request);

	public List<TravelPlanetDto> getAllTravel();

	public TravelPlanetDto getByTravelId(Integer id);

	public TravelPlanetDto update(Integer id, TravelPlanetRequest request);

	public String deletebyid(Integer id);
	
}
