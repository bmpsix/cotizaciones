package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.CurrencyExchange;

public interface CurrencyExchangeService {
	
	public abstract Consecutive getConsecutive();
	
	public abstract CurrencyExchange addCurrencyExchange(CurrencyExchange currencyExchange);
	
	public abstract List<CurrencyExchange> listAllCurrencyExchange();
	
	public abstract boolean removeCurrencyExchange(String idCurrencyExchange);
	
	public abstract void updatCurrencyExchange(CurrencyExchange currencyExchange);
	
	public abstract CurrencyExchange getCurrencyExchange(String idCurrencyExchange);
	
}
