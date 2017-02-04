package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.CollectMethod;

@Repository("collectMethodJpaRepository")
public interface CollectMethodJpaRepository extends JpaRepository<CollectMethod, Serializable>{

	Object findByDetail(String detail);
	
}