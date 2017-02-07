package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.IndustrySector;

public interface IndustrySectorService {
	public abstract void addIndustrySector(IndustrySector industrySector);
	
	public abstract List<IndustrySector> listAllIndustrySectors();	

	public abstract IndustrySector findById(String idIndustrySector);

}
