package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.User;

public interface ProposalService {

	
	public abstract String autoIncrement();

	public abstract Proposal addProposal(Proposal proposal, int idUser);
	
	public abstract List<Proposal> findByCountry(Country country);
	
	public abstract List<Proposal> findByContryAndUser(Country country, User user);

	public abstract String formatNumber(int valie);
	
	public abstract Proposal findByIdProposal(int idProposal);
}
