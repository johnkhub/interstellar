package com.johnk.interstellar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distances")
public class Distance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long did;

	@Column(name = "origin_planet")
	private String origin_planet;
	
	@Column(name = "next_planet")
	private String next_planet;
	
	@Column(name = "distance")
	private Double distance;

	public Distance() {}
	
	public Distance(String origin, String next, Double distance) {
		super();
		this.origin_planet =origin;
		this.next_planet = next;
		this.distance = distance;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getOrigin_planet() {
		return origin_planet;
	}

	public void setOrigin_planet(String origin_planet) {
		this.origin_planet = origin_planet;
	}

	public String getNext_planet() {
		return next_planet;
	}

	public void setNext_planet(String next_planet) {
		this.next_planet = next_planet;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((did == null) ? 0 : did.hashCode());
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((next_planet == null) ? 0 : next_planet.hashCode());
		result = prime * result + ((origin_planet == null) ? 0 : origin_planet.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distance other = (Distance) obj;
		if (did == null) {
			if (other.did != null)
				return false;
		} else if (!did.equals(other.did))
			return false;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (next_planet == null) {
			if (other.next_planet != null)
				return false;
		} else if (!next_planet.equals(other.next_planet))
			return false;
		if (origin_planet == null) {
			if (other.origin_planet != null)
				return false;
		} else if (!origin_planet.equals(other.origin_planet))
			return false;
		return true;
	}
	
}
