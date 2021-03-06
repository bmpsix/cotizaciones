package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.IndustrySector;

public interface IndustrySectorService {
	public abstract void addIndustrySector(IndustrySector industrySector, int idUser);
	
	public abstract List<IndustrySector> listAllIndustrySectors();	

	public abstract IndustrySector findById(int idIndustrySector);

	public long rowCount(); 
}
