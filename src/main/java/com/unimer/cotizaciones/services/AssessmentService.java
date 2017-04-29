package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Assessment;

public interface AssessmentService {
	
	public abstract void addAssessment(Assessment assessment);
	
	public abstract List<Assessment> listAllAssessment();	

	public abstract Assessment findById(int idAssessment);
	
	public abstract void updateAssessment(Assessment assessment);


}
