package com.johnk.interstellar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.johnk.interstellar.model.Distance;

public interface DistanceRepository extends JpaRepository<Distance,Long> {

}