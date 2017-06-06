package com.unimer.cotizaciones.services;

import java.util.List;


import com.unimer.cotizaciones.entities.ClientContact;
import com.unimer.cotizaciones.entities.Country;

public interface ClientContactService {

	public abstract void addClientContact(ClientContact clientContact, int idUser);
	
	public abstract List<ClientContact> listAllClientContact();
	
	public abstract List<ClientContact> findByActiveStatus();
	
	public abstract ClientContact findById(int idClientContact);

	public abstract List<ClientContact> findByCountry(Country country);
}
