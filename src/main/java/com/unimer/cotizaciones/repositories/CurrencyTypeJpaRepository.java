package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.CurrencyType;

@Repository("currencyTypeJpaRepository")
public interface CurrencyTypeJpaRepository extends JpaRepository<CurrencyType, Serializable>{
	public abstract CurrencyType findByIdCurrencyType(int idCurrencyType);
}