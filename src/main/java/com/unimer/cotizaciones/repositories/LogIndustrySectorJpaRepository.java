package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.LogIndustrySector;

@Repository("logIndustrySectorJpaRepository")
public interface LogIndustrySectorJpaRepository extends JpaRepository<LogIndustrySector, Serializable>{
	
}