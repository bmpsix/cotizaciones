package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.OperationType;

@Repository("operationTypeJpaRepository")
public interface OperationTypeJpaRepository extends JpaRepository<OperationType, Serializable>{
	
	public abstract OperationType findByDetail(String detail);
	
	public abstract OperationType findByIdOperationType(String idOperationType);
	
}