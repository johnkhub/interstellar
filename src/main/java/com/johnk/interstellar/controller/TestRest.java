package com.johnk.interstellar.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.johnk.interstellar.model.Distance;
import com.johnk.interstellar.model.PathPlanet;
import com.johnk.interstellar.model.Planet;
import com.johnk.interstellar.service.DistanceService;
import com.johnk.interstellar.service.Node;
import com.johnk.interstellar.service.PlanetService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/interstellar")
public class TestRest {

  @Autowired
  PlanetService planetService;
  @Autowired
  DistanceService distanceService;
  
  @GetMapping("/planets")
  public ResponseEntity<List<Planet>> getAllPlanets() {
    try {
      List<Planet> planets = new ArrayList<Planet>();
      planets=planetService.getAll();
      if (planets.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(planets,new HttpHeaders(), HttpStatus.OK);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,e.getMessage(),e);
    }
  }
  
  @GetMapping("/planets/{pid}")
  public ResponseEntity<Planet> getPlanetById(@PathVariable("pid") long pid) {
	  Planet planet =planetService.getById(pid);
    if (planet!=null) {
      return new ResponseEntity<Planet>(planet,new HttpHeaders(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PostMapping("/planets")
  public ResponseEntity<Planet> createPlanet(@RequestBody Planet inPlanet) {
	  	Planet cust = planetService.create(inPlanet);
		if(cust!=null)
		{
			return new ResponseEntity<>(cust,new HttpHeaders(), HttpStatus.CREATED);
	  	}
	  	else {
	  	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	  	}
  }
	
  @PutMapping("/planets/{id}")
  public ResponseEntity<Planet> updatePlanet(@PathVariable (value="id") Long id, @RequestBody Planet inPlanet) {
	  try {
		  Planet planet= planetService.update(id,inPlanet);
	    	if(planet!=null) {
	    	    return new ResponseEntity<>(planet,new HttpHeaders(), HttpStatus.CREATED);
	    	}
	    	else {
	    	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    	}
	    	
	    } catch (Exception e) {
	    	return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
  }
  
  @DeleteMapping("/planets/{pid}")
  public ResponseEntity<Boolean> deletePlanetById(@PathVariable("pid") long pid) {
	  boolean resp =planetService.deleteByID(pid);
    if (resp==true) {
      return new ResponseEntity<>(true,new HttpHeaders(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(false,new HttpHeaders(),HttpStatus.NOT_FOUND);
    }
  }
  
  @GetMapping("/distances")
  public ResponseEntity<List<Distance>> getAllDistances() {
    try {
      List<Distance> distances = new ArrayList<Distance>();
      distances=distanceService.getAll();
      if (distances.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(distances,new HttpHeaders(), HttpStatus.OK);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,e.getMessage(),e);
    }
  }

  @GetMapping("/mindist/{dest}")
  public ResponseEntity<Double> calculateDistance(@PathVariable("dest") String dest) {
	  Double distance =distanceService.calculateMinimumDistance(dest);
	  if (distance!=null) {
	      return new ResponseEntity<Double>(distance,new HttpHeaders(), HttpStatus.OK);
	  } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
  }
  @GetMapping("/minpath/{dest}")
  public ResponseEntity<List<PathPlanet>> findPath(@PathVariable("dest") String dest) {
	  try
	  {
		  List<PathPlanet> pathNodes =distanceService.findShortestPath(dest);
		  if (pathNodes.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(pathNodes,new HttpHeaders(), HttpStatus.OK);
	  } 
	  catch (Exception e) {
	      throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,e.getMessage(),e);
	   }
  }
  
}