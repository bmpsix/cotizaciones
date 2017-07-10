package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.AssessmentShared;
import com.unimer.cotizaciones.entities.User;

@Repository("assessmentSharedJpaRepository")
public interface AssessmentSharedJpaRepository extends JpaRepository<AssessmentShared, Serializable> {
	public abstract List<AssessmentShared> findByUser(User user);
	public abstract AssessmentShared findByIdAssessmentShared(int idAssessmentShared);
	public abstract List<AssessmentShared> findByUserShared(User user);
}
