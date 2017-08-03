package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.TechniquesByProposal;

public interface TechniqueByProposalService {

	public abstract void addTechniqueByProposal(int idTechnique,int idProposal, int idUser);
	
	public abstract List<TechniquesByProposal>  findTechiquesByProposal(Proposal proposal);
}
