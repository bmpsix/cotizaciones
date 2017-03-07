package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogStudyCategory;
import com.unimer.cotizaciones.entities.StudyCategory;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogStudyCategoryJpaRepository;
import com.unimer.cotizaciones.repositories.StudyCategoryJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.StudyCategoryService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("studyCategoryImpl")
public class StudyCategoryServiceImpl implements StudyCategoryService{

	
	@Autowired
	@Qualifier("studyCategoryJpaRepository")
	private StudyCategoryJpaRepository studyCategoryJpaRepository; 
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logStudyCategoryJpaRepository")
	private LogStudyCategoryJpaRepository logStudyCategoryJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	String ipCliente="";
	
	private static final Log LOG = LogFactory.getLog(StudyCategoryServiceImpl.class);
	
	@Override
	public StudyCategory  addStudyCategory(StudyCategory studyCategory) {

		Consecutive consecutive = consecutivesJpaRepository.findByType("Study category");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Study category");
			consecutive.setPrefix("STC");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Study category table");
			consecutivesJpaRepository.save(consecutive);
			studyCategory.setIdStudyCategory(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!studyCategory.getIdStudyCategory().equals(studyCategoryJpaRepository.findOne(studyCategory.getIdStudyCategory()))) {
				
				studyCategoryJpaRepository.save(studyCategory);
				LOG.info("METHOD: addStudyCategory in StudyCategoryServiceImpl -- PARAMS: " + studyCategory.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó una categoría de estudio");

			} else {
				updateStudyCategory(studyCategory);
			}

		} else if (studyCategory.getIdStudyCategory() == null) {

			studyCategory.setIdStudyCategory(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!studyCategory.getIdStudyCategory().equals(studyCategoryJpaRepository.findOne(studyCategory.getIdStudyCategory()))) {
				LOG.info("METHOD: addStudyCategory in StudyCategoryServiceImpl -- PARAMS: " + studyCategory.toString());
				studyCategoryJpaRepository.save(studyCategory);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó una categoría de estudio");
			} else {
				updateStudyCategory(studyCategory);
			}
		} else {
			updateStudyCategory(studyCategory);
		}
		return studyCategory;
	}

	
	@Override
	public List<StudyCategory> listAllStudyCategories() {
		return studyCategoryJpaRepository.findAll();
	}	

	@Override
	public StudyCategory findById(String idStudyCategory) {
		
		
		return studyCategoryJpaRepository.findByIdStudyCategory(idStudyCategory);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Study category");
	}

	private void updateStudyCategory(StudyCategory studyCategory) {
		java.util.Date date = new Date();
		StudyCategory studyCategoryToUpdate = studyCategoryJpaRepository.findByIdStudyCategory(studyCategory.getIdStudyCategory());
		if (studyCategoryToUpdate != null) {
			LogStudyCategory logStudyCategory = new LogStudyCategory(date, "Study category  modified", "test",studyCategoryToUpdate.getIdStudyCategory() ,studyCategoryToUpdate.getDetail());
			studyCategoryJpaRepository.save(studyCategory);
			logStudyCategoryJpaRepository.save(logStudyCategory);
			insertBinnacle("Se actualizó una categoría de estudio");
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
