package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.AssessmentShared;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.AssessmentSharedJpaRepository;
import com.unimer.cotizaciones.services.AssessmentSharedService;

@Service("assessmentSharedServiceImpl")
public class AssessmentSharedServiceImpl implements AssessmentSharedService {
	
	@Autowired
	@Qualifier("assessmentSharedJpaRepository")
	private AssessmentSharedJpaRepository assessmentSharedJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(AssessmentSharedServiceImpl.class);

	@Override
	public AssessmentShared addAssessmentShared(AssessmentShared assessmentShared, int idUser) {
		LOG.info(assessmentShared);
		return assessmentSharedJpaRepository.save(assessmentShared);
	}

	@Override
	public List<AssessmentShared> listAllAssessmentShared() {
		// TODO Auto-generated method stub
		return null;
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
	
	

}
