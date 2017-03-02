package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.ProposalType;

@Repository("proposalTypeJpaRepository")
public interface ProposalTypeJpaRepository extends JpaRepository<ProposalType, Serializable>{

	ProposalType findByIdProposalType(String idProposalType);
	
}