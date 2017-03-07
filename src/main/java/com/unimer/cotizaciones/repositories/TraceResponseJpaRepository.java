package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;
import com.unimer.cotizaciones.entities.TraceResponse;

@Repository("traceResponseJpaRepository")
public interface TraceResponseJpaRepository extends JpaRepository<TraceResponse, Serializable>{

	
	public abstract TraceResponse findByIdTraceResponse(String idTraceResponse);
}