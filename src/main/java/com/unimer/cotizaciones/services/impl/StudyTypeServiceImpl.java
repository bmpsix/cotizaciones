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

@Service("StudyTypeServiceImpl")
public class StudyTypeServiceImpl implements StudyTypeService{

	@Autowired
	@Qualifier("studyTypeJpaRepository")
	private StudyTypeJpaRepository StudyTypeJpaRepository; 
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logStudyTypeJpaRepository")
	private LogStudyTypeJpaRepository logStudyTypeJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(StudyTypeServiceImpl.class);
	
	@Override
	public StudyType  addStudyType(StudyType StudyType) {

		Consecutive consecutive = consecutivesJpaRepository.findByType("Study Type");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("StudyType");
			consecutive.setPrefix("STT");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Study Type table");
			consecutivesJpaRepository.save(consecutive);
			StudyType.setIdStudyType(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!StudyType.getIdStudyType().equals(StudyTypeJpaRepository.findOne(StudyType.getIdStudyType()))) {
				
				StudyTypeJpaRepository.save(StudyType);
				LOG.info("METHOD: addStudyType in StudyTypeServiceImpl -- PARAMS: " + StudyType.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				updateStudyType(StudyType);
			}

		} else if (StudyType.getIdStudyType() == null) {

			StudyType.setIdStudyType(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!StudyType.getIdStudyType().equals(StudyTypeJpaRepository.findOne(StudyType.getIdStudyType()))) {
				LOG.info("METHOD: addStudyType in StudyTypeServiceImpl -- PARAMS: " + StudyType.toString());
				StudyTypeJpaRepository.save(StudyType);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateStudyType(StudyType);
			}
		} else {
			updateStudyType(StudyType);
		}
		return StudyType;
	}

	
	@Override
	public List<StudyType> listAllStudyTypes() {
		return StudyTypeJpaRepository.findAll();
	}	

	@Override
	public StudyType findById(String idStudyType) {
		
		
		return StudyTypeJpaRepository.findByIdStudyType(idStudyType);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Study Type");
	}

	private void updateStudyType(StudyType StudyType) {
		java.util.Date date = new Date();
		StudyType StudyTypeToUpdate = StudyTypeJpaRepository.findByIdStudyType(StudyType.getIdStudyType());
		if (StudyTypeToUpdate != null) {
			LogStudyType logStudyType = new LogStudyType(date, "Study Type modified", "test",StudyTypeToUpdate.getIdStudyType() ,StudyTypeToUpdate.getDetail());
			StudyTypeJpaRepository.save(StudyType);
			logStudyTypeJpaRepository.save(logStudyType);
		}
	}
	

}