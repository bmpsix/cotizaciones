package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogAssessment;
import com.unimer.cotizaciones.repositories.AssessmentJpaRepository;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogAssessmentJpaRepository;
import com.unimer.cotizaciones.services.AssessmentService;

@Service("assessmentServiceImpl")
public class AssessmentServiceImpl implements AssessmentService {
	
	@Autowired
	@Qualifier("assessmentJpaRepository")
	private AssessmentJpaRepository assessmentJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logAssessmentJpaRepository")
	private LogAssessmentJpaRepository logAssessmentJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(AssessmentServiceImpl.class);
	
	@Override
	public Assessment addAssessment(Assessment assessment) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Assessment");
		assessment.setCreationDate(new Date());
		
		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Assessment");
			consecutive.setPrefix("ASE");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for Assessment");
			consecutivesJpaRepository.save(consecutive);
			assessment.setIdAssessment(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!assessment.getIdAssessment().equals(assessmentJpaRepository.findOne(assessment.getIdAssessment()))) {
				
				assessmentJpaRepository.save(assessment);
				LOG.info("METHOD: addAssessment in AssessmentServiceImpl -- PARAMS: " + assessment.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				updateAssessment(assessment);
			}

		} else if (assessment.getIdAssessment() == null) {

			assessment.setIdAssessment(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!assessment.getIdAssessment().equals(assessmentJpaRepository.findOne(assessment.getIdAssessment()))) {
				LOG.info("METHOD: addAssessment in AssessmentServiceImpl -- PARAMS: " + assessment.toString());
				assessmentJpaRepository.save(assessment);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateAssessment(assessment);
			}
		} else {
			updateAssessment(assessment);
		}
		return assessment;
	}

	@Override
	public List<Assessment> listAllAssessment() {
		return assessmentJpaRepository.findAll();
	}

	@Override
	public Assessment findById(String idAssessment) {
		return assessmentJpaRepository.findOne(idAssessment);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findOne("Assessment");
	}

	@Override
	public void updateAssessment(Assessment assessment) {

			java.util.Date date = new Date();
			Assessment asessmentToUpdate = assessmentJpaRepository.findOne(assessment.getIdAssessment());
			if (asessmentToUpdate != null) {
				LogAssessment logAssessment = new LogAssessment(date, "Assesssment  modified", "test", asessmentToUpdate.getCreationDate(),asessmentToUpdate.getDetail() ,
						asessmentToUpdate.getIdAssessment(),asessmentToUpdate.getCurrencyExchange().getIdCurrencyExchange(),asessmentToUpdate.getSaClient().getIdSaClient(),asessmentToUpdate.getUser().getIdUser());
				assessmentJpaRepository.save(assessment);
				logAssessmentJpaRepository.save(logAssessment);
			}
		
	}
	
	

}
