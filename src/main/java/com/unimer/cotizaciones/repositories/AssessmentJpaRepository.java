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
	
	public abstract List<Assessment> findByUser(User user);
	
	/*@Query("select A from Assessment A,User U where A.id_user = :iduser and U.id_country = :idcountry")
	public abstract List<Assessment> findByUserByCountry(@Param("iduser")String iduser,@Param("idcountry")String idcountry);*/
	
}
