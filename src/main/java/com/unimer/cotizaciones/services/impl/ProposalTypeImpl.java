package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogProposalType;
import com.unimer.cotizaciones.entities.ProposalType;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogProposalTypeJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalTypeJpaRepository;
import com.unimer.cotizaciones.services.ProposalTypeService;

@Service("proposalTypeImpl")
public class ProposalTypeImpl implements ProposalTypeService {

	@Autowired
	@Qualifier("proposalTypeJpaRepository")
	private ProposalTypeJpaRepository proposalTypeJpaRepository;
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logProposalTypeJpaRepository")
	private LogProposalTypeJpaRepository logProposalTypeJpaRepository;

	
	private static final Log LOG = LogFactory.getLog(ProposalTypeImpl.class);
	
	
	
	
	
	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Proposal type");
	}

	@Override
	public ProposalType addProposalType(ProposalType proposalType) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Proposal type");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Proposal type");
			consecutive.setPrefix("PRT-1");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Proposal Type");
			consecutivesJpaRepository.save(consecutive);
			proposalType.setIdProposalType(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!proposalType.getIdProposalType().equals(proposalTypeJpaRepository.findOne(proposalType.getIdProposalType()))) {
				
				proposalTypeJpaRepository.save(proposalType);
				LOG.info("METHOD: addProposalType in ProposalTypeServiceImpl -- PARAMS: " + proposalType.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				updateProposalType(proposalType);
			}

		} else if (proposalType.getIdProposalType() == null) {

			proposalType.setIdProposalType(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!proposalType.getIdProposalType().equals(proposalTypeJpaRepository.findOne(proposalType.getIdProposalType()))) {
				LOG.info("METHOD: addProposalType in ProposalTypeServiceImpl -- PARAMS: " + proposalType.toString());
				proposalTypeJpaRepository.save(proposalType);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateProposalType(proposalType);
			}
		} else {
			updateProposalType(proposalType);
		}
		
		return proposalType;
	}

	@Override
	public List<ProposalType> listAllProposalTypes() {
		return proposalTypeJpaRepository.findAll();
		
	}

	@Override
	public ProposalType findById(String idProposalType) {
		return proposalTypeJpaRepository.findOne(idProposalType);
	}
	
	private void updateProposalType(ProposalType proposalType) {
		java.util.Date date = new Date();
		ProposalType proposalTypeToUpdate = proposalTypeJpaRepository.findByIdProposalType(proposalType.getIdProposalType());
		if (proposalTypeToUpdate != null) {
			LogProposalType logProposalType = new LogProposalType(date, "Proposal Type  modified", "test", proposalTypeToUpdate.getDetail(), proposalTypeToUpdate.getIdProposalType());
			proposalTypeJpaRepository.save(proposalType);
			logProposalTypeJpaRepository.save(logProposalType);
		}

	

	}	

		
		
	

}
