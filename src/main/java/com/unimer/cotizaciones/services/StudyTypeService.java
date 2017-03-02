
package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.StudyType;

public interface StudyTypeService {

	public abstract StudyType addStudyType(StudyType StudyType);
	
	public abstract List<StudyType> listAllStudyTypes();	

	public abstract StudyType findById(String idStudyType);

	public abstract Consecutive getConsecutive();
	
	
}