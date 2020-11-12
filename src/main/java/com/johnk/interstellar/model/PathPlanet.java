package com.johnk.interstellar.model;

public class PathPlanet {
	private String name;
	private Double distanceTo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getDistanceTo() {
		return distanceTo;
	}
	public void setDistanceTo(Double distanceTo) {
		this.distanceTo = distanceTo;
	}
	public PathPlanet() {}
	public PathPlanet(String name, Double distanceTo) {
		super();
		this.name = name;
		this.distanceTo = distanceTo;
	}
	
}
