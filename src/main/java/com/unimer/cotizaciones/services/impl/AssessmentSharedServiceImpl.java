package com.unimer.cotizaciones.services.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.AssessmentShared;
import com.unimer.cotizaciones.entities.SaClient;
import com.unimer.cotizaciones.entities.Status;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.AssessmentJpaRepository;
import com.unimer.cotizaciones.repositories.AssessmentSharedJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalJpaRepository;
import com.unimer.cotizaciones.repositories.SaClientJpaRepository;
import com.unimer.cotizaciones.repositories.StatusJpaRepository;
import com.unimer.cotizaciones.services.AssessmentSharedService;

@Service("assessmentSharedServiceImpl")
public class AssessmentSharedServiceImpl implements AssessmentSharedService {
	
	@Autowired
	@Qualifier("assessmentSharedJpaRepository")
	private AssessmentSharedJpaRepository assessmentSharedJpaRepository;
	
	
	@Autowired
	@Qualifier("proposalJpaRepository")
	private ProposalJpaRepository proposalJpaRepository;
	
	@Autowired
	@Qualifier("saClientJpaRepository")
	private SaClientJpaRepository saClientJpaRepository;
	
	@Autowired
	@Qualifier("statusJpaRepository")
	private StatusJpaRepository statusJpaRepository;

	@Autowired
	@Qualifier("assessmentJpaRepository")
	private AssessmentJpaRepository assessmentJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(AssessmentSharedServiceImpl.class);

	@Override
	public AssessmentShared addAssessmentShared(AssessmentShared assessmentShared, int idUser) {
		LOG.info(assessmentShared);
		return assessmentSharedJpaRepository.save(assessmentShared);
	}


	@Override
	public List<AssessmentShared> listAllByUser(User user) {
		return assessmentSharedJpaRepository.findByUser(user);
	}

	@Override
	public AssessmentShared findById(int idAssessmentShared) {
		return assessmentSharedJpaRepository.findByIdAssessmentShared(idAssessmentShared);
	}

	@Override
	public void delete(AssessmentShared assessmentShared) {
		assessmentSharedJpaRepository.delete(assessmentShared);
	}

	@Override
	public List<AssessmentShared> listAllByUserShared(User user) {
		return assessmentSharedJpaRepository.findByUserShared(user);
	}

	@Override
	public AssessmentShared findByUserAndUserSharedAndAssessment(User user, User userShared, Assessment assessment) {
		return assessmentSharedJpaRepository.findByUserAndUserSharedAndAssessment(user, userShared, assessment);
	}


	@Override
	public void updateAssignedShared(Assessment assessment,User userAssign) {
		
		List<AssessmentShared> assessmentSharedToAssessment = assessmentSharedJpaRepository.findByAssessment(assessment);
		if(!assessmentSharedToAssessment.isEmpty())
		{
			for(AssessmentShared assessmentShared : assessmentSharedToAssessment)
			{
				if(assessment.getIdAssessment()==assessmentShared.getAssessment().getIdAssessment())
				{
					assessmentShared.setUser(userAssign);
					assessmentSharedJpaRepository.save(assessmentShared);
				}
			}
		}
		
		
	}


	@Override
	public int countProposalToAssessmentSharedByUserShared(AssessmentShared assessmentShared) {
		return proposalJpaRepository.countByUserAndAssessment(assessmentShared.getUserShared(), assessmentShared.getAssessment());
	}


	
	@Override
	public List<AssessmentShared> filterAssessmentSharedByUserShared(int idSAClientSearch, String creationDate, int idStatusSearch,
			User user) {
	
		
		
		List<AssessmentShared> assessmentSharedList = assessmentSharedJpaRepository.findByUserShared(user);
		List<AssessmentShared> assessmentSharedList2 = assessmentSharedJpaRepository.findByUserShared(user);
		SaClient saClient = saClientJpaRepository.findByIdSaClient(idSAClientSearch);
		Status status = statusJpaRepository.findByIdStatus(idStatusSearch);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		LOG.info("TAMAÑO LISTA 1: "+assessmentSharedList.size());
		
		 

			if(creationDate!=null && creationDate!="" && !assessmentSharedList.isEmpty()) 
			{
				for(AssessmentShared assessmentShared : assessmentSharedList)
				{
					String date = format.format(assessmentShared.getAssessment().getCreationDate());
					if(!date.equals(creationDate)) assessmentSharedList2.remove(assessmentShared); 
				}
			}
			
			
			if(saClient!=null && !assessmentSharedList.isEmpty()) 
				{
					for(AssessmentShared assessmentShared : assessmentSharedList)
						{
							if(assessmentShared.getAssessment().getSaClient() != saClient) assessmentSharedList2.remove(assessmentShared); 
						}
				}
			
			if(status!=null && !assessmentSharedList.isEmpty())
				{
				
					for(AssessmentShared assessmentShared : assessmentSharedList)
						{
							if(assessmentShared.getAssessment().getStatus() != status) assessmentSharedList2.remove(assessmentShared); 
						}
				}

			
			
		return assessmentSharedList2;
		 
		
	}
	

}
