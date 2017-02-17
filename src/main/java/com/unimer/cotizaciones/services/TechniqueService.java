package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.Technique;



public interface TechniqueService {

	public abstract Technique addSTechnique(Technique Technique);
	
	public abstract List<Technique> listAllTechniques();	

	public abstract Technique findById(String idTechnique);

	public abstract Consecutive getConsecutive();
	
	
}



