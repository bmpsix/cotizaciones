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
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogStudyCategoryJpaRepository;
import com.unimer.cotizaciones.repositories.StudyCategoryJpaRepository;
import com.unimer.cotizaciones.services.StudyCategoryService;

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
	
	private static final Log LOG = LogFactory.getLog(StudyCategoryServiceImpl.class);
	
	@Override
	public void addStudyCategory(StudyCategory studyCategory) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("StudyCategory");

		if (consecutive != null) {
			studyCategory.setIdStudyCategory(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!studyCategory.getIdStudyCategory().equals(studyCategoryJpaRepository.findOne(studyCategory.getIdStudyCategory()))
					&& studyCategoryJpaRepository.findByDetail(studyCategory.getDetail()) == null) {

				studyCategoryJpaRepository.save(studyCategory);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else if (studyCategoryJpaRepository.findByIdStudyCategory(studyCategory.getIdStudyCategory()) != null) {
				StudyCategory studyCategoryToUpdate = studyCategoryJpaRepository.findByIdStudyCategory(studyCategory.getIdStudyCategory());
				java.util.Date date = new Date();
				LogStudyCategory lstudyCategory = new LogStudyCategory(date, "Study Category  modified", "test", studyCategoryToUpdate.getDetail(),
				studyCategoryToUpdate.getIdStudyCategory());				
				
				studyCategoryJpaRepository.save(studyCategoryToUpdate);
				logStudyCategoryJpaRepository.save(lstudyCategory);

			}
		} else {

			Consecutive ConsecutiveStudyCategoryDefault = new Consecutive();

			ConsecutiveStudyCategoryDefault.setType("StudyCategory");
			ConsecutiveStudyCategoryDefault.setPrefix("STC");
			ConsecutiveStudyCategoryDefault.setSubfix(1);
			ConsecutiveStudyCategoryDefault.setDetail("Consecutivo por defecto para el manejo de las categoría de estudio");
			consecutivesJpaRepository.save(ConsecutiveStudyCategoryDefault);

			studyCategory.setIdStudyCategory(ConsecutiveStudyCategoryDefault.getPrefix() + "-" + ConsecutiveStudyCategoryDefault.getSubfix());

			if (!studyCategory.getIdStudyCategory().equals(studyCategoryJpaRepository.findOne(studyCategory.getIdStudyCategory()))
					&& studyCategoryJpaRepository.findByDetail(studyCategory.getDetail()) == null) {
				studyCategoryJpaRepository.save(studyCategory);
				ConsecutiveStudyCategoryDefault.setSubfix(ConsecutiveStudyCategoryDefault.getSubfix() + 1);
				consecutivesJpaRepository.save(ConsecutiveStudyCategoryDefault);
			} else {
				LOG.info("METHOD: addStudyCategory in StudyCategoryServiceImpl -- The studyCategory id already exists ");
				return;
			}
		}

		
	}

	@Override
	public List<StudyCategory> listAllStudyCategories() {
		List<StudyCategory> studyCategories = studyCategoryJpaRepository.findAll();
		return studyCategories;
	}



	@Override
	public StudyCategory findById(String idStudyCategory) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
