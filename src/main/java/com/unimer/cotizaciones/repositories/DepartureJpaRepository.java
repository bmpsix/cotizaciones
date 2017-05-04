package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;
import com.unimer.cotizaciones.entities.Departure;

@Repository("departureJpaRepository")

public interface DepartureJpaRepository extends JpaRepository<Departure, Serializable>{

	public abstract Departure findByDetail(String detail);

	public abstract Departure findByIdDeparture(int idDeparture);
	
}