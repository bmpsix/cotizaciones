package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Target;

@Repository("targetJpaRepository")
public interface TargetJpaRepository extends JpaRepository<Target, Serializable>{

	Target findByIdTarget(int idTarget);
	
}