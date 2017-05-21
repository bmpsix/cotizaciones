package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Client;

public interface ClientService {

	public abstract void addClient(Client client, int idUser);
	
	public abstract List<Client> listAllClient();
	
	public abstract Client findById(int idClient);
	
	public abstract List<Client> findByActiveStatus();

}
