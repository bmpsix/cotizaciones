package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.Operation;

public interface OperationService {
	public abstract Consecutive getConsecutive();
	
	public abstract Operation addOperation(Operation operation);
	
	public abstract List<Operation> listAllOperation();
	
	public abstract Operation findById(String idOperation);
	
	public abstract void IP(String string);
}
