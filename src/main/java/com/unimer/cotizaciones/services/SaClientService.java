package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.SaClient;

public interface SaClientService {

	public abstract void addSaClient(SaClient saClient);
	
	public abstract List<SaClient> listAllSaClient();
	
	public abstract void updateStatusById(int idSaClient, byte status);
	
	public abstract SaClient findById(int idSaClient);
	
	
	public abstract List<SaClient> findByActiveStatus();
}
