package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.ProposalDetails;

public interface ProposalDetailsService {

	public abstract List<ProposalDetails> findByProposal(Proposal proposal);
	
	public abstract void removeProposalDetailByIdProposalDetail(int idProposalDetail);
	
	public abstract void addProposalDetails(ProposalDetails proposalDetails, int idUser);
	
	
}
