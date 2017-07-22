package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.ProposalDetails;

@Repository("proposalDetailsJpaRepository")
public interface ProposalDetailsJpaRepository  extends JpaRepository<ProposalDetails, Serializable> {

	public abstract List<ProposalDetails> findByProposal(Proposal proposal);

	public abstract ProposalDetails findByIdProposalDetails(int idProposalDetails);

	
} 
