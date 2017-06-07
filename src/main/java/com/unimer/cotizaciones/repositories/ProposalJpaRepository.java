package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Proposal;

@Repository("proposalJpaRepository")
public interface ProposalJpaRepository extends JpaRepository<Proposal, Serializable>{
	
	@Query("select max(V.idProposal) from Proposal V")
	public abstract int autoIncrement();
	
}