package com.unimer.cotizaciones.services.impl;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.ProposalType;
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
	public void addProposalType(ProposalType proposalType, int idUser) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public List<ProposalType> listAllProposalTypes() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ProposalType findById(int idProposalType) {
		// TODO Auto-generated method stub
		return null;
	}



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
	
	
	private String formatNumber(int valie)
	{
		@SuppressWarnings("resource")
		Formatter fmt = new Formatter();
		return fmt.format("%04d",valie).toString();
	}
	
	
	
}
