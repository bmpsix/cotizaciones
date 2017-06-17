package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogStudyType;
import com.unimer.cotizaciones.entities.StudyType;
import com.unimer.cotizaciones.repositories.LogStudyTypeJpaRepository;
import com.unimer.cotizaciones.repositories.StudyTypeJpaRepository;
import com.unimer.cotizaciones.services.StudyTypeService;

@Service("StudyTypeServiceImpl")
public class StudyTypeServiceImpl implements StudyTypeService{

	@Autowired
	@Qualifier("studyTypeJpaRepository")
	private StudyTypeJpaRepository StudyTypeJpaRepository; 
	

	@Autowired
	@Qualifier("logStudyTypeJpaRepository")
	private LogStudyTypeJpaRepository logStudyTypeJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(StudyTypeServiceImpl.class);
	
	@Override
	public void  addStudyType(StudyType StudyType, int idUser) {


			if (StudyType.getIdStudyType()==0) {
				
				StudyTypeJpaRepository.save(StudyType);
				LOG.info("METHOD: addStudyType in StudyTypeServiceImpl -- PARAMS: " + StudyType.toString());

			} else {
				updateStudyType(StudyType,idUser);
			}

		} 

	
	@Override
	public List<StudyType> listAllStudyTypes() {
		return StudyTypeJpaRepository.findAll();
	}	

	@Override
	public StudyType findById(int idStudyType) {
		return StudyTypeJpaRepository.findByIdStudyType(idStudyType);
	}

	

	private void updateStudyType(StudyType StudyType, int idUser) {
		java.util.Date date = new Date();
		StudyType StudyTypeToUpdate = StudyTypeJpaRepository.findByIdStudyType(StudyType.getIdStudyType());
		if (StudyTypeToUpdate != null) {
			LogStudyType logStudyType = new LogStudyType(date, "Study Type modified", idUser,StudyTypeToUpdate.getDetail(),StudyTypeToUpdate.getIdStudyType() );
			StudyTypeJpaRepository.save(StudyType);
			logStudyTypeJpaRepository.save(logStudyType);
		}
	}
	
}