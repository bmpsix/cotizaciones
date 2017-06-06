package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.CurrencyType;

public interface CurrencyExchangeService {
	
	
	public abstract void addCurrencyExchange(CurrencyExchange currencyExchange, int idUser);
	
	public abstract List<CurrencyExchange> listAllCurrencyExchange();
	
	public abstract boolean removeCurrencyExchange(int idCurrencyExchange);
	
	public abstract CurrencyExchange getCurrencyExchange(int idCurrencyExchange);
	
	public abstract CurrencyExchange findByCountryAndCurrencyType(Country country, CurrencyType currencyType);
}
