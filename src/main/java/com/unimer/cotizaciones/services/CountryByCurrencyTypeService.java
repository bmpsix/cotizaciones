package com.unimer.cotizaciones.services;

import java.util.List;
import com.unimer.cotizaciones.entities.CountryByCurrencyType;

public interface CountryByCurrencyTypeService {
	
	public abstract List<CountryByCurrencyType> listAllCountryByCurrencyType();
	public abstract void addCountryByCurrencyType(CountryByCurrencyType countryByCurrencyType);
	
	
}
