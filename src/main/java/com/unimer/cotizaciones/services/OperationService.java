package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Operation;

public interface OperationService {
	public abstract void addOperation(Operation operation, int idUser);
	
	public abstract List<Operation> listAllOperation();
	
	public abstract Operation findById(int idOperation);
	
	public abstract Operation findByDetail(String detail);
	
}
