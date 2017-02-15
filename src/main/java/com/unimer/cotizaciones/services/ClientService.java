package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Client;
import com.unimer.cotizaciones.entities.Consecutive;

public interface ClientService {

	
	public abstract Consecutive getConsecutive();
	
	public abstract Client addClient(Client client);
	
	public abstract List<Client> listAllClient();
	
	public abstract Client findById(String idClient);
	
	
}
