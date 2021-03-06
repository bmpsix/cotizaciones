package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.User;

@Repository("proposalJpaRepository")
public interface ProposalJpaRepository extends JpaRepository<Proposal, Serializable>{
	
	@Query("select max(V.idProposal) from Proposal V")
	public abstract int autoIncrement();
	
	public abstract List<Proposal> findByAssessmentAndCountry(Assessment assessment,Country country);
	
	public abstract List<Proposal> findByAssessmentAndUser(Assessment assessment,User user);	
	
	public abstract int countByUserAndAssessment(User user,Assessment assessmnet);
	
	public abstract Proposal findByIdProposal(int idProposal);
	
	public abstract List<Proposal> findByAssessment(Assessment assessment);
	
	
}