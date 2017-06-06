package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.CurrencyType;

@Repository("currencyExchangeJpaRepository")
public interface CurrencyExchangeJpaRepository extends JpaRepository<CurrencyExchange, Serializable>{
	public abstract CurrencyExchange findByIdCurrencyExchange(int idCurrencyExchange);
	public abstract CurrencyExchange findByCountryAndCurrencyType(Country country, CurrencyType currencyType);
}