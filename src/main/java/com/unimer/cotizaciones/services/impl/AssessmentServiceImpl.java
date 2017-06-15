package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.LogAssessment;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.AssessmentJpaRepository;
import com.unimer.cotizaciones.repositories.LogAssessmentJpaRepository;
import com.unimer.cotizaciones.services.AssessmentService;

@Service("assessmentServiceImpl")
public class AssessmentServiceImpl implements AssessmentService {
	
	@Autowired
	@Qualifier("assessmentJpaRepository")
	private AssessmentJpaRepository assessmentJpaRepository;

	
	@Autowired
	@Qualifier("logAssessmentJpaRepository")
	private LogAssessmentJpaRepository logAssessmentJpaRepository;
	

	private static final Log LOG = LogFactory.getLog(AssessmentServiceImpl.class);
	
	
	@Override
	public Assessment addAssessment(Assessment assessment, int idUser) {
		
			if (assessment.getIdAssessment()==0) {
				java.util.Date date = new Date();
				assessment.setCreationDate(date);
				LOG.info("METHOD: addAssessment in AssessmentServiceImpl -- PARAMS: " + assessment.toString());
			    return assessmentJpaRepository.save(assessment);

			} else {
				return updateAssessment(assessment, idUser);
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

	
	
	private Assessment updateAssessment(Assessment assessment, int idUser) {

			java.util.Date date = new Date();
			Assessment asessmentToUpdate = assessmentJpaRepository.findByIdAssessment(assessment.getIdAssessment());
			if (asessmentToUpdate != null) {
				LogAssessment logAssessment = new LogAssessment(date, "Assesssment  modified", idUser, asessmentToUpdate.getCreationDate(),asessmentToUpdate.getDetail() ,
						asessmentToUpdate.getIdAssessment(),asessmentToUpdate.getCurrencyExchange().getIdCurrencyExchange(),asessmentToUpdate.getSaClient().getIdSaClient(),asessmentToUpdate.getUser().getIdUser());
				//assessment.setCreationDate(date);
				logAssessmentJpaRepository.save(logAssessment);
				return assessmentJpaRepository.save(assessment);
				
			}else{
				return null;
			}
		
	}

	@Override
	public List<Assessment> listAllByUser(User user) {
		return assessmentJpaRepository.findByUser(user);
	}
	

}
