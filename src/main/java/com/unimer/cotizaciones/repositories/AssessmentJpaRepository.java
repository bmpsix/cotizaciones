package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.User;

@Repository("assessmentJpaRepository")
public interface AssessmentJpaRepository extends JpaRepository<Assessment, Serializable>{

	public abstract Assessment  findByIdAssessment(int idAssessment);
	
	public abstract List<Assessment> findByUserAssigned(User userAssigned);

	
}
