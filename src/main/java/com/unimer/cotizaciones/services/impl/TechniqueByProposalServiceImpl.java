package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogTechniqueByProposal;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.Technique;
import com.unimer.cotizaciones.entities.TechniquesByProposal;
import com.unimer.cotizaciones.repositories.LogTechniqueByProposalJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalJpaRepository;
import com.unimer.cotizaciones.repositories.TechniqueByProposalJpaRepository;
import com.unimer.cotizaciones.repositories.TechniqueJpaRepository;
import com.unimer.cotizaciones.services.TechniqueByProposalService;


@Service("techniqueByProposalServiceImpl")
public class TechniqueByProposalServiceImpl implements TechniqueByProposalService {

	private static final Log LOG = LogFactory.getLog(TechniqueByProposalServiceImpl.class);
	
	
	@Autowired
	@Qualifier("TechniqueJpaRepository")
	private TechniqueJpaRepository techniqueJpaRepository;
	
	@Autowired
	@Qualifier("techniqueByProposalJpaRepository")
	private TechniqueByProposalJpaRepository techniqueByProposalJpaRepository;
	
	@Autowired
	@Qualifier("proposalJpaRepository")
	private ProposalJpaRepository proposalJpaRepository;
	
	@Autowired
	@Qualifier("logTechniqueByProposalJpaRepository")
	private  LogTechniqueByProposalJpaRepository logTechniqueByProposalJpaRepository;
	
	@Override
	public void addTechniqueByProposal(int idTechnique, int idProposal, int idUser) {
		

		LOG.info("METHOD: addTechniqueByProposal in TechniqueByProposalServiceImpl -- PARAMS: idTechnique: "+idTechnique+" idProposal: "+idProposal);
		Technique technique = techniqueJpaRepository.findByIdTechnique(idTechnique);
		Proposal proposal = proposalJpaRepository.findByIdProposal(idProposal);
		
		TechniquesByProposal techniqueByProposal = new TechniquesByProposal(proposal,technique);
		techniqueByProposalJpaRepository.save(techniqueByProposal);
		LOG.info("METHOD: addTechniqueByProposal in TechniqueByProposalServiceImpl  techniqueByProposal"+ techniqueByProposal);
		LogTechniqueByProposal logTechniqueByProposal = new LogTechniqueByProposal(new Date(),"add TechniqueByProposal",idUser,  idProposal,  idTechnique);
		logTechniqueByProposalJpaRepository.save(logTechniqueByProposal);

	}

	@Override
	public List<TechniquesByProposal>  findTechiquesByProposal(Proposal proposal) {
		return techniqueByProposalJpaRepository.findByProposal(proposal);
		
	}
	
	
	

}
