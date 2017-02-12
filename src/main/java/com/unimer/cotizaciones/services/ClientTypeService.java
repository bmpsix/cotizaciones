package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;

public interface ClientTypeService {

public abstract ClientTypeService addRol(ClientTypeService clientType);
	
	public abstract List<ClientTypeService> listAllClientType();
	
	public abstract void updateStatusById(String idClientType, byte status);
	
	public abstract ClientTypeService findById(String idClientType);
	
	public abstract Consecutive getConsecutive();
}
