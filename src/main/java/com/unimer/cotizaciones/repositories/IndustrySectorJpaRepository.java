package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.IndustrySector;


@Repository("industrySectorJpaRepository")
public interface IndustrySectorJpaRepository extends JpaRepository<IndustrySector, Serializable>{

	IndustrySector findByIdIndustrySector(int idIndustrySector);
	
}