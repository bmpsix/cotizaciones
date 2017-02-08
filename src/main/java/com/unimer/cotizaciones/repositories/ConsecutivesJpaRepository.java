package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;


import com.unimer.cotizaciones.entities.Consecutive;

@Repository("consecutivesJpaRepository")
public interface ConsecutivesJpaRepository extends JpaRepository<Consecutive, Serializable>{
	
	public abstract Consecutive findByType(String type);
	public abstract Consecutive findByPrefix(String prefix);
	
}