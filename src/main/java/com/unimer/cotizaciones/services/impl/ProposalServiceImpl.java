package com.unimer.cotizaciones.services.impl;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.LogProposalJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalJpaRepository;
import com.unimer.cotizaciones.services.ProposalService;

@Service("proposalServiceImpl")
public class ProposalServiceImpl implements ProposalService {

	@Autowired
	@Qualifier("proposalJpaRepository")
	private ProposalJpaRepository proposalJpaRepository;
	

	
	@Autowired
	@Qualifier("logProposalJpaRepository")
	private LogProposalJpaRepository logProposalJpaRepository;




	@Override
	public String autoIncrement() {
		try
		{
			
			if(proposalJpaRepository.autoIncrement()!=0) 
				{
					return formatNumber((proposalJpaRepository.autoIncrement()+1));
				}
			else return formatNumber(1);
		}
		catch(Exception e)
		{
			return formatNumber(1);
		}
	}
	
	@Override
	public String formatNumber(int valie)
	{
		@SuppressWarnings("resource")
		Formatter fmt = new Formatter();
		return fmt.format("%04d",valie).toString();
	}



	@Override
	public Proposal addProposal(Proposal proposal, int idUser) {
		return proposalJpaRepository.save(proposal);
		
	}


	@Override
	public List<Proposal> findByCountry(Country country) {
		return proposalJpaRepository.findByCountry(country);
	}


	@Override
	public List<Proposal> findByContryAndUser(Country country, User user) {
		return proposalJpaRepository.findByCountryAndUser(country, user);
	}

	@Override
	public Proposal findByIdProposal(int idProposal) {
		return proposalJpaRepository.findByIdProposal(idProposal);
	}

	@Override
	public List<Proposal> findByAssessment(Assessment assessement) {
		return proposalJpaRepository.findByAssessment(assessement);
	}
	
	
	
}
