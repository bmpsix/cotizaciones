package com.unimer.cotizaciones.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.HeadUserToUser;
import com.unimer.cotizaciones.entities.LogAssessment;
import com.unimer.cotizaciones.entities.SaClient;
import com.unimer.cotizaciones.entities.Status;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.AssessmentJpaRepository;
import com.unimer.cotizaciones.repositories.HeadUserToUserJpaRepository;
import com.unimer.cotizaciones.repositories.LogAssessmentJpaRepository;
import com.unimer.cotizaciones.repositories.SaClientJpaRepository;
import com.unimer.cotizaciones.repositories.StatusJpaRepository;
import com.unimer.cotizaciones.services.AssessmentService;

@Service("assessmentServiceImpl")
public class AssessmentServiceImpl implements AssessmentService {
	
	@Autowired
	@Qualifier("assessmentJpaRepository")
	private AssessmentJpaRepository assessmentJpaRepository;
	
	@Autowired
	@Qualifier("headUserToUserJpaRepository")
	private HeadUserToUserJpaRepository headUserToUserJpaRepository;
	
	@Autowired
	@Qualifier("saClientJpaRepository")
	private SaClientJpaRepository saClientJpaRepository;
	
	@Autowired
	@Qualifier("statusJpaRepository")
	private StatusJpaRepository statusJpaRepository;

	
	@Autowired
	@Qualifier("logAssessmentJpaRepository")
	private LogAssessmentJpaRepository logAssessmentJpaRepository;
	

	private static final Log LOG = LogFactory.getLog(AssessmentServiceImpl.class);
	
	
	@Override
	public void addAssessment(Assessment assessment, int idUser) {
		
			if (assessment.getIdAssessment()==0) {
				java.util.Date date = new Date();
				assessment.setCreationDate(date);
				LOG.info("METHOD: addAssessment in AssessmentServiceImpl -- PARAMS: " + assessment.toString());
			    assessmentJpaRepository.save(assessment);

			} else {
				 updateAssessment(assessment, idUser);
			}
	}

	@Override
	public List<Assessment> listAllAssessment() {
		return assessmentJpaRepository.findAll();
	}

	@Override
	public Assessment findById(int idAssessment) {
		return assessmentJpaRepository.findByIdAssessment(idAssessment);
	}

	@Override
	public List<Assessment> listAllByUserAssign(User user) {
		return assessmentJpaRepository.findByUserAssigned(user);
	}

	@Override
	public List<Assessment> listAllAssessmentToHeadUser(User user) {
		
		List<Assessment> listAssessmentToHeadUser = assessmentJpaRepository.findByUserAssigned(user);
		List<HeadUserToUser> listHeadUserToUser = headUserToUserJpaRepository.findUserByHeadUser(user);
		
		if (!listHeadUserToUser.isEmpty()) {
			for (HeadUserToUser headUserToUser : listHeadUserToUser) 
			{
				if (headUserToUser.getHeadUser().getIdUser() == user.getIdUser()) 
				{
					List<Assessment> listAssessmentToUser = assessmentJpaRepository.findByUserAssigned(headUserToUser.getUser());
					if (!listAssessmentToUser.isEmpty()) for (Assessment assessmentToUser : listAssessmentToUser) listAssessmentToHeadUser.add(assessmentToUser);
				}
			}
		}
		return listAssessmentToHeadUser;
	}
	
	@Override
	public List<Assessment> listAllAssessmentByUserCountry(User user) {
		
		List<Assessment> listAllAssessment = assessmentJpaRepository.findAll();
		if(!listAllAssessment.isEmpty())
		{
			for(Assessment assessment : listAllAssessment) if(assessment.getUserAssigned().getCountry().getIdCountry()!=user.getCountry().getIdCountry()) listAllAssessment.remove(assessment);
		}
		return listAllAssessment;
	}
	
	private void updateAssessment(Assessment assessment, int idUser) {

			java.util.Date date = new Date();
			Assessment asessmentToUpdate = assessmentJpaRepository.findByIdAssessment(assessment.getIdAssessment());
			LOG.info("METHOD: updateAssessment assessmentToUpdate in AssessmentServiceImpl -- PARAMS: " + asessmentToUpdate.toString());
			LOG.info("METHOD: updateAssessment assessment in AssessmentServiceImpl -- PARAMS: " + assessment.toString());
			if (asessmentToUpdate != null) {
				LogAssessment logAssessment = new LogAssessment(date, "Assesssment  modified", idUser, asessmentToUpdate.getCreationDate(),asessmentToUpdate.getDetail() ,
						asessmentToUpdate.getIdAssessment(),asessmentToUpdate.getCurrencyExchange(),asessmentToUpdate.getSaClient().getIdSaClient(),asessmentToUpdate.getUser().getIdUser());
				//assessment.setCreationDate(date);
				assessmentJpaRepository.save(assessment);
				logAssessmentJpaRepository.save(logAssessment);
				LOG.info("METHOD: updateAssessment in AssessmentServiceImpl -- PARAMS: " + assessment.toString());
				
				
			}
		
	}

	@Override
	public List<Assessment> filterAssessment(int idSAClientSearch, String creationDate, int idStatusSearch,User user) {
		
		List<Assessment> assessmentList;
		List<Assessment> assessmentList2;
		SaClient saClient = saClientJpaRepository.findByIdSaClient(idSAClientSearch);
		Status status = statusJpaRepository.findByIdStatus(idStatusSearch);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		
		if(user.getRol().getDetail().toUpperCase().equals("BOSS_CONTRIBUTOR")) 
		{
			assessmentList= listAllAssessmentToHeadUser(user);
			assessmentList2= listAllAssessmentToHeadUser(user);
		}
		else 
		{
			if(user.getRol().getDetail().toUpperCase().equals("ADMIN") || user.getRol().getDetail().toUpperCase().equals("ADMINISTRATOR")) 
			{
					assessmentList= listAllAssessmentByUserCountry(user);
					assessmentList2= listAllAssessmentByUserCountry(user);
			}	
			else 
			{
				assessmentList= listAllByUserAssign(user);
				assessmentList2= listAllByUserAssign(user);
			}
		}
		
		
		if(creationDate!=null && creationDate!="" && !assessmentList.isEmpty()) 
		{
			for(Assessment assessment : assessmentList)
			{
				String date = format.format(assessment.getCreationDate());
				if(!date.equals(creationDate)) assessmentList2.remove(assessment); 
			}
		}
		
		
		if(saClient!=null && !assessmentList.isEmpty()) 
			{
				for(Assessment assessment : assessmentList)
					{
						if(assessment.getSaClient() != saClient) assessmentList2.remove(assessment); 
					}
			}
		
		if(status!=null && !assessmentList.isEmpty())
			{
			
				for(Assessment assessment : assessmentList)
					{
						if(assessment.getStatus() != status) assessmentList2.remove(assessment); 
					}
			}

		
		
		return assessmentList2;
	}

	



	

}
