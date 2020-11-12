package com.johnk.interstellar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnk.interstellar.model.Distance;
import com.johnk.interstellar.model.PathPlanet;
import com.johnk.interstellar.model.Planet;
import com.johnk.interstellar.repository.DistanceRepository;


@Service
public class DistanceService {

	@Autowired
	DistanceRepository distanceRepository;
	@Autowired
	PlanetService planetService;
	
	public List<Distance> getAll(){
		List<Distance> distList=distanceRepository.findAll();
		if(distList.size()>0) {
			return distList;
		}
		else {
			return new ArrayList<Distance>();
		}
	}
	
	public Distance getById(Long id) {
		Optional<Distance> dist=distanceRepository.findById(id);
		if(dist.isPresent()) {
			return dist.get();
		}
		else {
			return null;
		}
	}
	
	public Distance createOrUpdate(Distance inDist) { 
		if(inDist.getDid() != null) {
			Optional<Distance> dist=distanceRepository.findById(inDist.getDid());
			if(dist.isPresent()) {
				Distance newDist=dist.get();
				newDist.setOrigin_planet(inDist.getOrigin_planet());
				newDist.setNext_planet(inDist.getNext_planet());
				newDist.setDistance(inDist.getDistance());
				newDist=distanceRepository.save(newDist);
				return newDist;
			}
			else {
				Distance newDist=distanceRepository.save(inDist);
				return newDist;
			}
				
		}
		else {
			return null;
		}
	}
	
	public void deleteById(long id) {//throws
		Optional<Distance>dist=distanceRepository.findById(id);
		if(dist.isPresent()) {
			distanceRepository.deleteById(id);
		}
		else {
			//return null;//thows
		}
	}
	
	private void constructGraph() {
		List<Planet> planets=planetService.getAll();
        for(int x=0;x<planets.size();x++){
            Node d = new Node(planets.get(x).getPlanet_name());
            map.put(planets.get(x).getPlanet_name(),d);   
        }     
       distances=this.getAll();
	   for(int aa=0;aa<distances.size();aa++){
		   
		   
	       for (java.util.Map.Entry pair : map.entrySet()) {   
	            if(distances.get(aa).getOrigin_planet().equals(pair.getKey())){
	                   map.get(distances.get(aa).getOrigin_planet()).addNeighbour(new Edge(map.get(distances.get(aa).getOrigin_planet()),map.get(distances.get(aa).getNext_planet()),distances.get(aa).getDistance()));
	            }
	        }
	   }
	   // Set source planet/node
	   this.shortestPath.findShortestPath(map.get("A"));
	}
	
	private java.util.Map<String, Node> map = new java.util.HashMap<String, Node>();
	private ShortestPathAlgorithm shortestPath = new ShortestPathAlgorithm();
	private java.util.List<Distance> distances;
	
	
	public List<PathPlanet> findShortestPath(String destP){
		this.constructGraph(); 
		List<PathPlanet>path=shortestPath.getShortestPath(this.map.get(destP),distances);
		return path;
	}
	
	public Double calculateMinimumDistance(String destP){
		this.constructGraph();
		Double minDist=map.get(destP).getDist();
		return minDist;
	}

}