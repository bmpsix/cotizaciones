package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.ExecutionType;

public interface ExecutionTypeService {


	public abstract ExecutionType addExecutionType(ExecutionType executionType);
	
	public abstract List<ExecutionType> listAllExecutionType();
	
	//public abstract void updateStatusById(String idExecutionType, byte status);
	
	public abstract ExecutionType findById(String idExecutionType);
	
	public abstract Consecutive getConsecutive();
	
}
