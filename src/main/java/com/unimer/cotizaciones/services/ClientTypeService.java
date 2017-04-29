package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.ClientType;

public interface ClientTypeService {

public abstract void addClientType(ClientType clientType);
	
	public abstract List<ClientType> listAllClientType();
	
	public abstract void updateStatusById(int idClientType, byte status);
	
	public abstract ClientType findById(int idClientType);
	
	
	public abstract List<ClientType> findByActiveStatus();
	
}
