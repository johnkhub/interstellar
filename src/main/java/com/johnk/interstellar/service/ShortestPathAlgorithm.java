package com.johnk.interstellar.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import com.johnk.interstellar.model.Distance;
import com.johnk.interstellar.model.PathPlanet;

public class ShortestPathAlgorithm {
	
	public void findShortestPath(Node sourceV){
		sourceV.setDist(0);
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceV);
		sourceV.setVisited(true);
       
		while( !priorityQueue.isEmpty() ){
			Node actualVertex = priorityQueue.poll();
			for(Edge edge : actualVertex.getList()){
			Node v = edge.getToVertex();
				
				if(!v.Visited())
				{
					double newDistance = actualVertex.getDist() + edge.getDist();
                    if( newDistance < v.getDist() ){
                    	priorityQueue.remove(v);
						v.setDist(newDistance);
						v.setPr(actualVertex);
						priorityQueue.add(v);
					}
				}
			}
			  actualVertex.setVisited(true);
		}
	}
 
	public List<PathPlanet> getShortestPath(Node targetVertex,java.util.List<Distance> m){
		List<PathPlanet> path = new ArrayList<>();
		PathPlanet pathPlanet;
		for(Node vertex=targetVertex;vertex!=null;vertex=vertex.getPr()){
			pathPlanet=new PathPlanet();
			pathPlanet.setName(vertex.getName());
			Double dd=.0;
			for (Distance d : m) {
				if(d.getNext_planet().equals(vertex.getName())) {
					dd=d.getDistance();
				}
			   }
			
			pathPlanet.setDistanceTo(dd);
			path.add(pathPlanet);
		}
		Collections.reverse(path);
		return path;
	}
 
}