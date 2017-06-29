package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Departure;

public interface DepartureService {
	
public abstract void addDeparture(Departure departure, int idUser);
	
	public abstract List<Departure> listAllDeparture();
	
	public abstract Departure findById(int idDeparture);
	
	public abstract Departure findByDetail(String detail);
	
	public abstract List<Departure> findDepartureByCountry(Country country);
	
	public abstract List<Departure> findDepartureByCountryAndStatus(Country country,byte status);

}
