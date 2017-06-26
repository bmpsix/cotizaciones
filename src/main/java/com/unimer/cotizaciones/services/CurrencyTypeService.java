package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.CurrencyType;

public interface CurrencyTypeService {
	
	
	public abstract void addCurrencyType(CurrencyType currencyType, int idUser);
	
	public abstract List<CurrencyType> listAllCurrencyType();
	
	public abstract CurrencyType getCurrencyType(int idCurrencyType);
	
	public abstract CurrencyType findByDetail(String detail);
	
}
