package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Departure;

@Repository("departureJpaRepository")

public interface DepartureJpaRepository extends JpaRepository<Departure, Serializable>{

	public abstract Departure findByDetail(String detail);

	public abstract Departure findByIdDeparture(int idDeparture);
	
	public abstract List<Departure> findDepartureByCountry(Country country);
	
	public abstract List<Departure> findDepartureByCountryAndStatus(Country country, byte status);
	
}