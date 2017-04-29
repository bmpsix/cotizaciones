package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.CurrencyExchange;

public interface CurrencyExchangeService {
	
	
	public abstract void addCurrencyExchange(CurrencyExchange currencyExchange);
	
	public abstract List<CurrencyExchange> listAllCurrencyExchange();
	
	public abstract boolean removeCurrencyExchange(int idCurrencyExchange);
	
	public abstract CurrencyExchange getCurrencyExchange(int idCurrencyExchange);
	
	
}
