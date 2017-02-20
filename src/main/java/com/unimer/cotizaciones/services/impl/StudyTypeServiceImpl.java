package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogStudyType;
import com.unimer.cotizaciones.entities.StudyType;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogStudyTypeJpaRepository;
import com.unimer.cotizaciones.repositories.StudyTypeJpaRepository;
import com.unimer.cotizaciones.services.StudyTypeService;

@Service("studyTypeImpl")

public class StudyTypeServiceImpl implements StudyTypeService{

	@Autowired
	@Qualifier("studyTypeJpaRepository")
	private StudyTypeJpaRepository studyTypeJpaRepository; 
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logStudyTypeJpaRepository")
	private LogStudyTypeJpaRepository logStudyTypeJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(StudyTypeServiceImpl.class);
	
	@Override
	public StudyType  addStudyType(StudyType studyType) {

		Consecutive consecutive = consecutivesJpaRepository.findByType("Study type");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Study type");
			consecutive.setPrefix("STT");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Study type table");
			consecutivesJpaRepository.save(consecutive);
			studyType.setIdStudyType(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!studyType.getIdStudyType().equals(studyTypeJpaRepository.findOne(studyType.getIdStudyType()))) {
				
				studyTypeJpaRepository.save(studyType);
				LOG.info("METHOD: addStudyType in StudyTypeServiceImpl -- PARAMS: " + studyType.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				updateStudyType(studyType);
			}

		} else if (studyType.getIdStudyType() == null) {

			studyType.setIdStudyType(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!studyType.getIdStudyType().equals(studyTypeJpaRepository.findOne(studyType.getIdStudyType()))) {
				LOG.info("METHOD: addStudyType in StudyTypeServiceImpl -- PARAMS: " + studyType.toString());
				studyTypeJpaRepository.save(studyType);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateStudyType(studyType);
			}
		} else {
			updateStudyType(studyType);
		}
		return studyType;
	}

	
	@Override
	public List<StudyType> listAllStudyTypes() {
		return studyTypeJpaRepository.findAll();
	}	

	@Override
	public StudyType findById(String idStudyType) {
		
		
		return studyTypeJpaRepository.findByIdStudyType(idStudyType);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Study type");
	}

	private void updateStudyType(StudyType studyType) {
		java.util.Date date = new Date();
		StudyType studyTypeToUpdate = studyTypeJpaRepository.findByIdStudyType(studyType.getIdStudyType());
		if (studyTypeToUpdate != null) {
			LogStudyType logStudyType = new LogStudyType(date, "Study type  modified", "test",studyTypeToUpdate.getIdStudyType() ,studyTypeToUpdate.getDetail());
			studyTypeJpaRepository.save(studyType);
			logStudyTypeJpaRepository.save(logStudyType);
		}
	}
	

}