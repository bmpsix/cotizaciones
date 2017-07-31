package com.unimer.cotizaciones.services.impl;

import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.Client;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.HeadUserToUser;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.Status;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.ClientJpaRepository;
import com.unimer.cotizaciones.repositories.HeadUserToUserJpaRepository;
import com.unimer.cotizaciones.repositories.LogProposalJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalJpaRepository;
import com.unimer.cotizaciones.repositories.StatusJpaRepository;
import com.unimer.cotizaciones.services.ProposalService;

@Service("proposalServiceImpl")
public class ProposalServiceImpl implements ProposalService {

	@Autowired
	@Qualifier("proposalJpaRepository")
	private ProposalJpaRepository proposalJpaRepository;
	
	
	@Autowired
	@Qualifier("clientJpaRepository")
	private ClientJpaRepository clientJpaRepository;
	
	@Autowired
	@Qualifier("statusJpaRepository")
	private StatusJpaRepository statusJpaRepository;

	@Autowired
	@Qualifier("headUserToUserJpaRepository")
	private HeadUserToUserJpaRepository headUserToUserJpaRepository;
	
	@Autowired
	@Qualifier("logProposalJpaRepository")
	private LogProposalJpaRepository logProposalJpaRepository;

	private static final Log LOG = LogFactory.getLog(ProposalService.class);


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
	public List<Proposal> findByCountry(Assessment assessment,Country country) {
		return proposalJpaRepository.findByAssessmentAndCountry(assessment,country);
	}


	@Override
	public List<Proposal> findByUser(Assessment assessment, User user) {
		return proposalJpaRepository.findByAssessmentAndUser(assessment, user);
	}

	@Override
	public Proposal findByIdProposal(int idProposal) {
		return proposalJpaRepository.findByIdProposal(idProposal);
	}

	@Override
	public List<Proposal> findByAssessment(Assessment assessement) {
		return proposalJpaRepository.findByAssessment(assessement);
	}

	@Override
	public List<Proposal> filterProposal(Assessment assessment, int idClient, String initialDate, String endDate,int idStatus) {
	
		Client client = clientJpaRepository.findByIdClient(idClient);
		Status status = statusJpaRepository.findByIdStatus(idStatus);
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	       
		LOG.info("SERVICIO CONTENIDO DE INITIALdate "  + initialDate+" CONTENIDO DE ENDdete "+endDate+" ESTE ES EL ASSESSMENT "+assessment.toString());
		List<Proposal> allProposal =  proposalJpaRepository.findByAssessment(assessment);
		List<Proposal> allProposal2 =  proposalJpaRepository.findByAssessment(assessment);
		LOG.info("SERVICIO CONTENIDO DE INITIALdate "  + initialDate+" CONTENIDO DE ENDdete "+endDate+" ESTE ES EL ASSESSMENT "+assessment.toString()+ " CANTIDAD DE PROPUESTAS "+allProposal.size() );
		
		
		
		if(initialDate!=null && initialDate!="" && !allProposal.isEmpty()) 
		{
			for(Proposal proposal : allProposal)
			{
				String date = format.format(proposal.getInitialDate());
				if(!date.equals(initialDate)) allProposal2.remove(proposal); 
			}
		}
		if(endDate!=null && endDate!="" && !allProposal.isEmpty()) 
			{
				for(Proposal proposal : allProposal)
					{
						String date = format.format(proposal.getEndDate());
						if(!date.equals(endDate)) allProposal2.remove(proposal); 
						
					}
			}
		if(client!=null && !allProposal.isEmpty()) 
			{
				for(Proposal proposal : allProposal)
					{
						if(proposal.getClientContact().getClient() != client) allProposal2.remove(proposal); 
					}
			}
		if(status!=null && !allProposal.isEmpty()) 
			{
				for(Proposal proposal : allProposal)
					{
						if(proposal.getStatus() != status) allProposal2.remove(proposal); 
					}
			}
		
		
		return allProposal2;
	}

	@Override
	public List<Proposal> findByHeadUser(Assessment assessment,User user) {
		
		List<Proposal> listProposalToHeadUser = proposalJpaRepository.findByAssessmentAndUser(assessment, user);
		List<HeadUserToUser> listHeadUserToUser = headUserToUserJpaRepository.findUserByHeadUser(user);
		
		if (!listHeadUserToUser.isEmpty()) {
			for (HeadUserToUser headUserToUser : listHeadUserToUser) 
			{
				if (headUserToUser.getHeadUser().getIdUser() == user.getIdUser()) 
				{
					List<Proposal> listProposalToUser = proposalJpaRepository.findByAssessmentAndUser(assessment,headUserToUser.getUser());
					if (!listProposalToUser.isEmpty()) for (Proposal proposalToUser : listProposalToUser) listProposalToHeadUser.add(proposalToUser);
				}
			}
		}
		
		return listProposalToHeadUser;
	}
	
	
	
}
