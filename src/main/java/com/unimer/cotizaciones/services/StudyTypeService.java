
package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.StudyType;

public interface StudyTypeService {

	public abstract void addStudyType(StudyType StudyType, int idUser);
	
	public abstract List<StudyType> listAllStudyTypes();	

	public abstract StudyType findById(int idStudyType);
	
}