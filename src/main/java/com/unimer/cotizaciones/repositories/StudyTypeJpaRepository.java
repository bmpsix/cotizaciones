package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;
import com.unimer.cotizaciones.entities.StudyType;

@Repository("studyTypeJpaRepository")
public interface StudyTypeJpaRepository extends JpaRepository<StudyType, Serializable>{

	public abstract StudyType findByDetail(String detail);
	
	public abstract StudyType findByIdStudyType(int idStudyType);
	
}