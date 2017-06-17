package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogStudyCategory;
import com.unimer.cotizaciones.entities.StudyCategory;
import com.unimer.cotizaciones.repositories.LogStudyCategoryJpaRepository;
import com.unimer.cotizaciones.repositories.StudyCategoryJpaRepository;
import com.unimer.cotizaciones.services.StudyCategoryService;

@Service("studyCategoryImpl")
public class StudyCategoryServiceImpl implements StudyCategoryService{

	
	@Autowired
	@Qualifier("studyCategoryJpaRepository")
	private StudyCategoryJpaRepository studyCategoryJpaRepository; 
	
	@Autowired
	@Qualifier("logStudyCategoryJpaRepository")
	private LogStudyCategoryJpaRepository logStudyCategoryJpaRepository;

	private static final Log LOG = LogFactory.getLog(StudyCategoryServiceImpl.class);
	
	@Override
	public void  addStudyCategory(StudyCategory studyCategory, int idUser) {

	if (studyCategory.getIdStudyCategory()==0) {
				
				studyCategoryJpaRepository.save(studyCategory);
				LOG.info("METHOD: addStudyCategory in StudyCategoryServiceImpl -- PARAMS: " + studyCategory.toString());
				

			} else {
				updateStudyCategory(studyCategory, idUser);
			}

		
	}

	
	@Override
	public List<StudyCategory> listAllStudyCategories() {
		return studyCategoryJpaRepository.findAll();
	}	

	@Override
	public StudyCategory findById(int idStudyCategory) 
	{		
		return studyCategoryJpaRepository.findByIdStudyCategory(idStudyCategory);
	}


	private void updateStudyCategory(StudyCategory studyCategory, int idUser) {
		java.util.Date date = new Date();
		StudyCategory studyCategoryToUpdate = studyCategoryJpaRepository.findByIdStudyCategory(studyCategory.getIdStudyCategory());
		if (studyCategoryToUpdate != null) {
			LogStudyCategory logStudyCategory = new LogStudyCategory(date, "Study category  modified", idUser,studyCategoryToUpdate.getIdStudyCategory() ,studyCategoryToUpdate.getDetail());
			studyCategoryJpaRepository.save(studyCategory);
			logStudyCategoryJpaRepository.save(logStudyCategory);
			
		}
	}
	
	@Override
	public long rowCount() 
	{		
		return studyCategoryJpaRepository.count();
	}
	

}
