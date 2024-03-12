package com.studentcrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcrud.entity.TravelPlanet;

@Repository
public interface TravelPlanetRepo extends JpaRepository<TravelPlanet,Integer> {

}
