package com.johnk.interstellar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.johnk.interstellar.model.Planet;
import com.johnk.interstellar.repository.PlanetRepository;


@Service
public class PlanetService {

	@Autowired
	PlanetRepository planetRepository;
	
	public List<Planet> getAll(){
		List<Planet> planetList=planetRepository.findAll();
		if(planetList.size()>0) {
			return planetList;
		}
		else {
			return new ArrayList<Planet>();
		}
	}
	
	public Planet getById(Long id) {
		Optional<Planet> pdt=planetRepository.findById(id);
		if(pdt.isPresent()) {
			return pdt.get();
		}
		else {
			return null;
		}
	}
	
	public Planet update(Long id,Planet inPlanet) { 
		if(id != null) {
			Optional<Planet> planet=planetRepository.findById(id);
			if(planet.isPresent()) {
				Planet newPlanet=planet.get();
				newPlanet.setPlanet_name(inPlanet.getPlanet_name());
				newPlanet=planetRepository.save(inPlanet);
				return newPlanet;
			}
			else {
				return null;
			}
				
		}
		else {
			return null;
		}
	}
	
	public Planet create(Planet inPlanet) { 
		Planet newPlanet=planetRepository.save(inPlanet);
		return newPlanet;
	}

	public Boolean deleteByID(Long id) {
		Optional<Planet>planet=planetRepository.findById(id);
		if(planet.isPresent()) {
			planetRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}

}

