package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.User;

public interface ProposalService {

	
	public abstract String autoIncrement();

	public abstract Proposal addProposal(Proposal proposal, int idUser);
	
	public abstract List<Proposal> findByCountry(Assessment assessment, Country country);
	
	public abstract List<Proposal> findByUser(Assessment assessment, User user);

	public abstract String formatNumber(int valie);
	
	public abstract Proposal findByIdProposal(int idProposal);
	
	public abstract List<Proposal> findByAssessment(Assessment assessment);
	
	public abstract List<Proposal> filterProposal(Assessment assessment, int idClient, String initialDate, String endDate,int idStatus);
	
	public abstract List<Proposal> findByHeadUser(Assessment assessment,User user);
	
	
}
