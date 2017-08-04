package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.Technique;
import com.unimer.cotizaciones.entities.TechniquesByProposal;

public interface TechniqueByProposalService {

	public abstract void addTechniqueByProposal(int idTechnique,int idProposal, int idUser);
	
	public abstract List<TechniquesByProposal>  findTechiquesByProposal(Proposal proposal);
	
	public abstract void deleteTechiquesByProposal(Proposal proposal);
	
	public abstract List<Technique> othersTechniques(Proposal proposal);
}
