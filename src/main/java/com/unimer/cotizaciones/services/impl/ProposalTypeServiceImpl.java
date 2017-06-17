package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.unimer.cotizaciones.entities.LogProposalType;
import com.unimer.cotizaciones.entities.ProposalType;
import com.unimer.cotizaciones.repositories.LogProposalTypeJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalTypeJpaRepository;
import com.unimer.cotizaciones.services.ProposalTypeService;

@Service("proposalTypeServiceImpl")
@SessionAttributes({"userSession"})
public class ProposalTypeServiceImpl implements ProposalTypeService {

	@Autowired
	@Qualifier("proposalTypeJpaRepository")
	private ProposalTypeJpaRepository proposalTypeJpaRepository;
	
	
	@Autowired
	@Qualifier("logProposalTypeJpaRepository")
	private LogProposalTypeJpaRepository logProposalTypeJpaRepository;

	
	
	private static final Log LOG = LogFactory.getLog(ProposalTypeServiceImpl.class);
	

	@Override
	public void addProposalType(ProposalType proposalType, int idUser) {
		
			if (proposalType.getIdProposalType()==0) {
				
				proposalTypeJpaRepository.save(proposalType);
				LOG.info("METHOD: addProposalType in ProposalTypeServiceImpl -- PARAMS: " + proposalType.toString());
			

			} else {
				updateProposalType(proposalType,idUser);
			}

		} 

	@Override
	public List<ProposalType> listAllProposalTypes() {
		return proposalTypeJpaRepository.findAll();
		
	}

	@Override
	public ProposalType findById(int idProposalType) {
		return proposalTypeJpaRepository.findByIdProposalType(idProposalType);
	}
	
	private void updateProposalType(ProposalType proposalType, int idUser) {
		java.util.Date date = new Date();
		ProposalType proposalTypeToUpdate = proposalTypeJpaRepository.findByIdProposalType(proposalType.getIdProposalType());
		if (proposalTypeToUpdate != null) {
			LogProposalType logProposalType = new LogProposalType(date, "Proposal Type  modified",idUser, proposalTypeToUpdate.getDetail(), proposalTypeToUpdate.getIdProposalType());
			proposalTypeJpaRepository.save(proposalType);
			logProposalTypeJpaRepository.save(logProposalType);
		}
	}	

	

	@Override
	public long rowCount() {
		
		return proposalTypeJpaRepository.count();
	}
		
		
	

}
