package com.unimer.cotizaciones.services;

import java.util.List;


import com.unimer.cotizaciones.entities.ClientContact;

public interface ClientContactService {

	public abstract void addClientContact(ClientContact clientContact);
	
	public abstract List<ClientContact> listAllClientContact();
	
	public abstract List<ClientContact> findByActiveStatus();
	
	public abstract ClientContact findById(int idClientContact);

	
}
