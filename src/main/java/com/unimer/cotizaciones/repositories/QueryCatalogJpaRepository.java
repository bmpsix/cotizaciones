package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Client;
import com.unimer.cotizaciones.entities.QueryCatalog;

@Repository("queryCatalogJpaRepository")
public interface QueryCatalogJpaRepository extends JpaRepository<QueryCatalog, Serializable>{
	
	public abstract long count();
	public abstract QueryCatalog findByDetail(String detail);

}
