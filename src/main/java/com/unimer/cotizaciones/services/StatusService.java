package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.Status;

public interface StatusService {

public abstract Status addStatus(Status status);
	
	public abstract List<Status> listAllStatus();
	
	//public abstract void updateStatusById(String idStatus, byte status);
	
	public abstract Status findById(String idStatus);
	
	public abstract Consecutive getConsecutive();
}
