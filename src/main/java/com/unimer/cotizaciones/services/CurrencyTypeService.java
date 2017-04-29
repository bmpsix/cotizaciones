package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.CurrencyType;

public interface CurrencyTypeService {
	
	
	public abstract void addCurrencyType(CurrencyType currencyType);
	
	public abstract List<CurrencyType> listAllCurrencyType();
	
	public abstract void updateCurrencyType(CurrencyType currencyType);
	
	public abstract CurrencyType getCurrencyType(int idCurrencyType);
	
}
