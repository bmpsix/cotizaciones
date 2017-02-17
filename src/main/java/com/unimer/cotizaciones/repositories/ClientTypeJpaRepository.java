package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unimer.cotizaciones.entities.ClientType;

@Repository("clientTypeJpaRepository")
public interface ClientTypeJpaRepository extends JpaRepository<ClientType, Serializable> {

	public abstract ClientType findByDetail(String detail);

	public abstract ClientType findByIdClientType(String idClientType);
	
	public abstract List<ClientType> findByStatus(byte status);
}