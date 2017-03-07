package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.OperationType;

public interface OperationTypeService {

public abstract OperationType addOperationType(OperationType operationType);
	
	public abstract List<OperationType> listAllOperationType();
	
	public abstract OperationType findById(String idOperationType);
	
	public abstract Consecutive getConsecutive();
	
	public abstract void IP(String string);
}
