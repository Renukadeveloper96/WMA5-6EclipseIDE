package com.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentcrud.dto.TravelPlanetDto;
import com.studentcrud.dto.TravelPlanetRequest;
import com.studentcrud.entity.TravelPlanet;
import com.studentcrud.repo.TravelPlanetRepo;

@Service
public class TravelPlanetServiceImpl implements TravelPlanetService {

	@Autowired
	private TravelPlanetRepo tpoRepo;
	@Override
	public TravelPlanetDto createPlanet(TravelPlanetRequest request) {//request to entity
		TravelPlanet tp=new TravelPlanet();
		tp.setHotel(request.getHotel());
		tp.setArrival(request.getArrival());
		tp.setDeparture(request.getDeparture());
		tp.setGuests(request.getGuests());
		tp.setPrice(request.getPrice());
		tp.setType(request.getType());
		tpoRepo.save(tp);
		
		//entity to dto
		
		TravelPlanetDto dto=new TravelPlanetDto();
		dto.setId(tp.getId());
		dto.setHotel(tp.getHotel());
		dto.setArrival(tp.getArrival());
		dto.setDeparture(tp.getDeparture());
		dto.setGuests(tp.getGuests());
		dto.setPrice(tp.getPrice());
		dto.setType(tp.getType());
		return dto;
	}
	@Override
	public List<TravelPlanetDto> getAllTravel() {
		List<TravelPlanet> entity=tpoRepo.findAll();
		return entity.stream().map(tp->{
			TravelPlanetDto dto=new TravelPlanetDto();
			dto.setId(tp.getId());
			dto.setHotel(tp.getHotel());
			dto.setArrival(tp.getArrival());
			dto.setDeparture(tp.getDeparture());
			dto.setGuests(tp.getGuests());
			dto.setPrice(tp.getPrice());
			dto.setType(tp.getType());
			return dto;
		}).collect(Collectors.toList());
	}
	@Override
	public TravelPlanetDto getByTravelId(Integer id) {
		TravelPlanet entity=tpoRepo.findById(id).get();
		TravelPlanetDto dto=new TravelPlanetDto();
		dto.setId(entity.getId());
		dto.setHotel(entity.getHotel());
		dto.setArrival(entity.getArrival());
		dto.setDeparture(entity.getDeparture());
		dto.setGuests(entity.getGuests());
		dto.setPrice(entity.getPrice());
		dto.setType(entity.getType());
		return dto;
	}
	@Override
	public TravelPlanetDto update(Integer id, TravelPlanetRequest request) {//request to entity
		TravelPlanet entity=tpoRepo.findById(id).get();
		entity.setHotel(request.getHotel());
		entity.setArrival(request.getArrival());
		entity.setDeparture(request.getDeparture());
		entity.setGuests(request.getGuests());
		entity.setType(request.getType());
		entity.setPrice(request.getPrice());
		tpoRepo.save(entity);
		
		//entity to dto
		TravelPlanetDto dto=new TravelPlanetDto();
		dto.setId(entity.getId());
		dto.setHotel(entity.getHotel());
		dto.setArrival(entity.getArrival());
		dto.setDeparture(entity.getDeparture());
		dto.setGuests(entity.getGuests());
		dto.setType(entity.getType());
		dto.setPrice(entity.getPrice());
		return dto;
	}
	@Override
	public String deletebyid(Integer id) {
		TravelPlanet entity=tpoRepo.findById(id).get();
		tpoRepo.delete(entity);
		return "deleted";
	}

}
