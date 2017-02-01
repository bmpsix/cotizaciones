package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;



public interface ConsecutiveService {

	
	public abstract Consecutive addConsecutive(Consecutive consecutive);
	
	public abstract List<Consecutive> listAllConsecutives();
	
	public abstract Consecutive findConsecutiveById(int id);
	
	public abstract void removeConsecutive(int id);
	
	
}
