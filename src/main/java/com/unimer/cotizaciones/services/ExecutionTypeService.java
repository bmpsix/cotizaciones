package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.ExecutionType;

public interface ExecutionTypeService {


	public abstract void addExecutionType(ExecutionType executionType, int idUser);
	
	public abstract List<ExecutionType> listAllExecutionType();
	
	public abstract ExecutionType findById(int idExecutionType);
	

	
}
