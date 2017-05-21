package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Status;

public interface StatusService {

public abstract void addStatus(Status status, int idUser);
	
	public abstract List<Status> listAllStatus();
	
	//public abstract void updateStatusById(String idStatus, byte status);
	
	public abstract Status findById(int idStatus);

}
