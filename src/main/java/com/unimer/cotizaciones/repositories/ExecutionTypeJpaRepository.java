package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.ExecutionType;

@Repository("executionTypeJpaRepository")
public interface ExecutionTypeJpaRepository extends JpaRepository<ExecutionType, Serializable> {
	
	public abstract ExecutionType findByDetail(String detail);

	public abstract ExecutionType findByIdExecutionType(int idExecutionType);
}