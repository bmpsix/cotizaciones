package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.ProposalType;

public interface ProposalService {

	public abstract Consecutive getConsecutive();
	
	public abstract ProposalType addProposalType(ProposalType proposalType);
	
	public abstract List<ProposalType> listAllProposalTypes();
	
	public abstract ProposalType findById(String idProposalType);
}
