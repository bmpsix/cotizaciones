package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Technique;



public interface TechniqueService {

	public abstract void addTechnique(Technique Technique, int idUser);
	
	public abstract List<Technique> listAllTechniques();	

	public abstract Technique findById(int idTechnique);
	
	public abstract List<Technique> orderlistAllTechniques();
}



