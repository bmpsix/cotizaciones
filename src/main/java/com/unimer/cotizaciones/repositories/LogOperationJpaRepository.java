package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.LogOperation;

@Repository("logOperationJpaRepository")
public interface LogOperationJpaRepository extends JpaRepository<LogOperation, Serializable>{
	
}