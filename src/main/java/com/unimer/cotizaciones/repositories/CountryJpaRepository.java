package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Country;


@Repository("countryJpaRepository")
public interface CountryJpaRepository extends JpaRepository<Country, Serializable>{
	
	
	public abstract Country findByDetail(String detail);
	
	public abstract Country findByIdCountry(int idCountry);

	public abstract Object findByCod(String cod);
}