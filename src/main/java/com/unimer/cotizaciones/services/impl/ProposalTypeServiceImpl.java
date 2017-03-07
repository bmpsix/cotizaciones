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
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogProposalTypeJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalTypeJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.ProposalTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("proposalTypeServiceImpl")
public class ProposalTypeServiceImpl implements ProposalTypeService {

	@Autowired
	@Qualifier("proposalTypeJpaRepository")
	private ProposalTypeJpaRepository proposalTypeJpaRepository;
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logProposalTypeJpaRepository")
	private LogProposalTypeJpaRepository logProposalTypeJpaRepository;

	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	String ipCliente="";
	
	
	private static final Log LOG = LogFactory.getLog(ProposalTypeServiceImpl.class);
	
	
	
	
	
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
			consecutive.setPrefix("PRT");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Proposal Type");
			consecutivesJpaRepository.save(consecutive);
			proposalType.setIdProposalType(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!proposalType.getIdProposalType().equals(proposalTypeJpaRepository.findOne(proposalType.getIdProposalType()))) {
				
				proposalTypeJpaRepository.save(proposalType);
				LOG.info("METHOD: addProposalType in ProposalTypeServiceImpl -- PARAMS: " + proposalType.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó un tipo de propuesta");

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

				insertBinnacle("Se agregó un tipo de propuesta");
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

			insertBinnacle("Se actualizó un tipo de propuesta");
		}
	}	

	@Override
	public void IP(String ip) {
		ipCliente=ip;
		
	}
	
	private void insertBinnacle(String msg)
	{
		Date date = new Date();
		TraceResponse traceResponse = new TraceResponse(null,"test",msg,ipCliente,date);
		traceResponseService.addTraceResponse(traceResponse);
	}
		
		
	

}
