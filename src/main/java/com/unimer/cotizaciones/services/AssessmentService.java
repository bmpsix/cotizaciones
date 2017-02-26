package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.Consecutive;

public interface AssessmentService {
	
	public abstract Assessment addAssessment(Assessment assessment);
	
	public abstract List<Assessment> listAllAssessment();	

	public abstract Assessment findById(String idAssessment);

	public abstract Consecutive getConsecutive();
	
	public abstract void updateAssessment(Assessment assessment);

}
