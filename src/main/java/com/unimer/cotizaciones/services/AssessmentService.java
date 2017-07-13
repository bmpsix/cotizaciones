package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.User;

public interface AssessmentService {
	
	public abstract void addAssessment(Assessment assessment, int idUser);
	
	public abstract List<Assessment> listAllAssessment();
	
	public abstract List<Assessment> listAllByUserAssign(User user);

	public abstract Assessment findById(int idAssessment);
	
	public abstract List<Assessment> listAllAssessmentToHeadUser(User user);
	
	public abstract List<Assessment> listAllAssessmentByUserCountry(User user);
}
