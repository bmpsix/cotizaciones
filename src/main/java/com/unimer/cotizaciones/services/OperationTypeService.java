package com.unimer.cotizaciones.services;

import java.util.List;
import com.unimer.cotizaciones.entities.OperationType;

public interface OperationTypeService {

public abstract void addOperationType(OperationType operationType);
	
	public abstract List<OperationType> listAllOperationType();
	
	public abstract OperationType findById(int idOperationType);
	
	public abstract OperationType findByDetail(String detail);
	
}
