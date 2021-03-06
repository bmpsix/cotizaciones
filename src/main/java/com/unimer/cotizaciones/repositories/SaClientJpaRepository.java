package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.SaClient;

@Repository("saClientJpaRepository")
public interface SaClientJpaRepository extends JpaRepository<SaClient, Serializable>{
	
	public abstract SaClient findByDetail(String detail);
	
	public abstract SaClient findByIdSaClient(int idSaClient);
	
	public abstract List<SaClient> findByStatus(byte status);
}