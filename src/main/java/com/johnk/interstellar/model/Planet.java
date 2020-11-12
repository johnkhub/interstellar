package com.johnk.interstellar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "planets")
public class Planet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;

	@Column(name = "planet_name")
	private String planet_name;
	
	public Planet() {}
	
	public Planet(String pname) {
		super();
		this.planet_name = pname;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPlanet_name() {
		return planet_name;
	}

	public void setPlanet_name(String planet_name) {
		this.planet_name = planet_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((planet_name == null) ? 0 : planet_name.hashCode());
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
		Planet other = (Planet) obj;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (planet_name == null) {
			if (other.planet_name != null)
				return false;
		} else if (!planet_name.equals(other.planet_name))
			return false;
		return true;
	}

}
