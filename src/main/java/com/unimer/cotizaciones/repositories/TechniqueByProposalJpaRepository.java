package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.TechniquesByProposal;

@Repository("techniqueByProposalJpaRepository")
public interface TechniqueByProposalJpaRepository extends JpaRepository<TechniquesByProposal, Serializable> {

	
	public abstract List<TechniquesByProposal> findByProposal(Proposal proposal);
	
}
