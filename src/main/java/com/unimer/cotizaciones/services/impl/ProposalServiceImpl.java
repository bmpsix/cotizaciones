package com.unimer.cotizaciones.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogProposalTypeJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalTypeJpaRepository;


public class ProposalServiceImpl {

	@Autowired
	@Qualifier("proposalJpaRepository")
	private ProposalTypeJpaRepository proposalTypeJpaRepository;
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logProposalJpaRepository")
	private LogProposalTypeJpaRepository logProposalTypeJpaRepository;
	
	
}
