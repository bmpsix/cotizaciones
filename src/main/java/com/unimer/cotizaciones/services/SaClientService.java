package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.SaClient;

public interface SaClientService {

	public abstract SaClient addSaClient(SaClient saClient);
	
	public abstract List<SaClient> listAllSaClient();
	
	public abstract void updateStatusById(String idSaClient, byte status);
	
	public abstract SaClient findById(String idSaClient);
	
	public abstract Consecutive getConsecutive();
}
