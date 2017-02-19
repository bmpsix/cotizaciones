package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;


import com.unimer.cotizaciones.entities.Operation;

@Repository("operationJpaRepository")
public interface OperationJpaRepository extends JpaRepository<Operation, Serializable>{
	
	
	public abstract Operation findByDetail(String detail);

	public abstract Operation  findByIdOperation(String idOperation);
	
}