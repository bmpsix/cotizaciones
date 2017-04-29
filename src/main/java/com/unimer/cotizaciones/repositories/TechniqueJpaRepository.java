package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Technique;

@Repository("TechniqueJpaRepository")
public interface TechniqueJpaRepository extends JpaRepository<Technique, Serializable>{

	public abstract Technique findByDetail(String detail);
	
	public abstract Technique findByIdTechnique(int idTechnique);
	
}
