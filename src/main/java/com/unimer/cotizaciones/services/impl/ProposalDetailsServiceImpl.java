package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.ProposalDetails;
import com.unimer.cotizaciones.repositories.ProposalDetailsJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalJpaRepository;
import com.unimer.cotizaciones.services.ProposalDetailsService;

@Service("proposalDetailsServiceImpl")
public class ProposalDetailsServiceImpl implements ProposalDetailsService{

	
	@Autowired
	@Qualifier("proposalDetailsJpaRepository")
	private ProposalDetailsJpaRepository proposalDetailsJpaRepository;
	
	@Autowired
	@Qualifier("proposalJpaRepository")
	private ProposalJpaRepository proposalJpaRepository;
	
	
	@Override
	public List<ProposalDetails> findByProposal(Proposal proposal) {
		return proposalDetailsJpaRepository.findByProposal(proposal);
		
	}

	@Override
	public void removeProposalDetailByIdProposalDetail(int idProposalDetail) {
		proposalDetailsJpaRepository.deleteById(idProposalDetail);
		
	}

	@Override
	public void addProposalDetails(ProposalDetails proposalDetails, int idUser) {
		proposalDetailsJpaRepository.save(proposalDetails);
		
		
	}

	@Override
	public ProposalDetails findById(int idProposalDetails) {
		
		return proposalDetailsJpaRepository.findByIdProposalDetails(idProposalDetails);
	}

}
