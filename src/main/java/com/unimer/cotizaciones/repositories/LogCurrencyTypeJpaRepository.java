package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.LogCurrencyType;

@Repository("logCurrencyTypeJpaRepository")
public interface LogCurrencyTypeJpaRepository extends JpaRepository<LogCurrencyType, Serializable>{
	
}