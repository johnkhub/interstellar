package com.johnk.interstellar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.johnk.interstellar.model.Planet;

public interface PlanetRepository extends JpaRepository<Planet,Long> {

}
