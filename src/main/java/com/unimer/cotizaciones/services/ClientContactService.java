package com.unimer.cotizaciones.services;

import java.util.List;


import com.unimer.cotizaciones.entities.ClientContact;
import com.unimer.cotizaciones.entities.Consecutive;

public interface ClientContactService {

	public abstract ClientContact addClientContact(ClientContact clientContact);
	
	public abstract List<ClientContact> listAllClientContact();
	
	public abstract List<ClientContact> findByActiveStatus();
	
	public abstract ClientContact findById(String idClientContact);
	
	public abstract Consecutive getConsecutive();
	
	public abstract void IP(String string);
	
}
