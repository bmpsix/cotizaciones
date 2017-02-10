package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.Country;


public interface CountryService {
	
	public abstract Country addCountry(Country country);
	
	public abstract List<Country> listAllCountries();	

	public abstract Country findById(String idCountry);

	public abstract Consecutive getConsecutive();
}
