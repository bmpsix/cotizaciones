package com.unimer.cotizaciones.services;

import java.util.List;
import com.unimer.cotizaciones.entities.Departure;

public interface DepartureService {
	
public abstract void addDeparture(Departure departure);
	
	public abstract List<Departure> listAllDeparture();
	
	public abstract Departure findById(int idDeparture);
	
	public abstract Departure findByDetail(String detail);

}
