package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.LogRol;

@Repository("logRolJpaRepository")
public interface LogRolJpaRepository extends JpaRepository<LogRol, Serializable>{
	
}