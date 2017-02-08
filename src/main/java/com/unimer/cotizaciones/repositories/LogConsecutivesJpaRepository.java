package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.LogConsecutive;

@Repository("logConsecutivesJpaRepository")
public interface LogConsecutivesJpaRepository extends JpaRepository<LogConsecutive, Serializable>{
	public abstract LogConsecutive findByPrefix(String prefix);
}