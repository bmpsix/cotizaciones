package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.StudyCategory;



public interface StudyCategoryService {

	public abstract void addStudyCategory(StudyCategory studyCategory, int idUser);
	
	public abstract List<StudyCategory> listAllStudyCategories();	

	public abstract StudyCategory findById(int idStudyCategory);

	public long rowCount();
	
}
