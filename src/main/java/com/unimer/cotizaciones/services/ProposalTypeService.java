package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.ProposalType;

public interface ProposalTypeService {
		
	public abstract void addProposalType(ProposalType proposalType,int idUser);
	
	public abstract List<ProposalType> listAllProposalTypes();
	
	public abstract ProposalType findById(int idProposalType);
	
	
	public long rowCount();

}