package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;


import com.unimer.cotizaciones.entities.StudyCategory;

@Repository("studyCategoryJpaRepository")
public interface StudyCategoryJpaRepository extends JpaRepository<StudyCategory, Serializable>{

	public abstract StudyCategory findByDetail(String detail);
	
	public abstract StudyCategory findByIdStudyCategory(String idStudyCategory);
	
}