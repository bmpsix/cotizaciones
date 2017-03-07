package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.CurrencyType;

public interface CurrencyTypeService {
	
	public abstract Consecutive getConsecutive();
	
	public abstract CurrencyType addCurrencyType(CurrencyType currencyType);
	
	public abstract List<CurrencyType> listAllCurrencyType();
	
	public abstract void updateCurrencyType(CurrencyType currencyType);
	
	public abstract CurrencyType getCurrencyType(String idCurrencyType);
	
	public abstract void IP(String ip);
	
}
