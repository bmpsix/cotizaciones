package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unimer.cotizaciones.entities.ClientType;

@Repository("clientTypeJpaRepository")
public interface ClientTypeJpaRepository extends JpaRepository<ClientType, Serializable> {

	public abstract ClientType findByDetail(String detail);

	public abstract ClientType findByIdClientType(String idClientType);
}