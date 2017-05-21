package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.SaClient;

public interface SaClientService {

	public abstract void addSaClient(SaClient saClient, int idUser);
	
	public abstract List<SaClient> listAllSaClient();
	
	public abstract SaClient findById(int idSaClient);
	
	
	public abstract List<SaClient> findByActiveStatus();
}
