package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Country;


public interface CountryService {
	
	public abstract void addCountry(Country country);
	
	public abstract List<Country> listAllCountries();	

	public abstract Country findById(int idCountry);
}
