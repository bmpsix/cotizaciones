package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.ClientType;
import com.unimer.cotizaciones.entities.Consecutive;

public interface ClientTypeService {

public abstract ClientType addClientType(ClientType clientType);
	
	public abstract List<ClientType> listAllClientType();
	
	public abstract void updateStatusById(String idClientType, byte status);
	
	public abstract ClientType findById(String idClientType);
	
	public abstract Consecutive getConsecutive();
}
