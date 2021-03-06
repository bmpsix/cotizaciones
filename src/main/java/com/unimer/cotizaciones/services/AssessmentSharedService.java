package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.AssessmentShared;
import com.unimer.cotizaciones.entities.User;

public interface AssessmentSharedService {
	
	public abstract AssessmentShared addAssessmentShared(AssessmentShared assessmentShared, int idUser);
	
	public abstract List<AssessmentShared> listAllByUser(User user);
	
	public abstract List<AssessmentShared> listAllByUserShared(User user);

	public abstract AssessmentShared findById(int idAssessmentShared);
	
	public abstract void delete(AssessmentShared assessmentShared);
	
	public abstract AssessmentShared findByUserAndUserSharedAndAssessment(User user, User userShared, Assessment assessment);
	
	public abstract void updateAssignedShared(Assessment assessment, User userAssign);
	
	public abstract int countProposalToAssessmentSharedByUserShared(AssessmentShared assessmentShared);
	
	public abstract List<AssessmentShared> filterAssessmentSharedByUserShared(int  idSAClientSearch, String creationDate, int idStatusSearch, User user);
}
