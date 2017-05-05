package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Country;


public interface CountryByCurrencyTypeService {
	
	public abstract List<Country> listAllCountryByCurrencyType();
	public abstract void addCountryByCurrencyType(int idCountry,int idCurrencyType);
	
	
}
