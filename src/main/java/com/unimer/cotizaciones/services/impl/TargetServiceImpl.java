package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogTarget;
import com.unimer.cotizaciones.entities.Target;
import com.unimer.cotizaciones.services.TargetService;
import com.unimer.cotizaciones.repositories.LogTargetJpaRepository;
import com.unimer.cotizaciones.repositories.TargetJpaRepository;

@Service("targetServiceImpl")
public class TargetServiceImpl implements TargetService {
	
	@Autowired
	@Qualifier("targetJpaRepository")
	private TargetJpaRepository targetJpaRepository;
	@Autowired
	@Qualifier("logTargetJpaRepository")
	private LogTargetJpaRepository logTargetJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(TargetServiceImpl.class);
	
	
	@Override
	public void addTarget(Target target, int idUser) {
		
			if (target.getIdTarget()==0) {
				
				targetJpaRepository.save(target);
				LOG.info("METHOD: addtarget in targetServiceImpl -- PARAMS: " + target.toString());
			} else {
				updateTarget(target, idUser);
			}
		} 
	@Override
	public List<Target> listAllTargets() {
	
		return targetJpaRepository.findAll();
	}

	@Override
	public Target findById(int idTarget) {
	
		return targetJpaRepository.findByIdTarget(idTarget);
	}

	private void updateTarget(Target target, int idUser)
	
	{
		java.util.Date date = new Date();
		Target targetToUpdate = targetJpaRepository.findByIdTarget(target.getIdTarget());
		if (targetToUpdate != null)
		{
			LogTarget logTarget = new LogTarget(date, "Target  modified", idUser, targetToUpdate.getCreationDate(), targetToUpdate.getEndDate(),targetToUpdate.getIdTarget(),targetToUpdate.getInitialDate(),targetToUpdate.getObservations());
			LOG.info("METHOD: updateTarget in targetServiceImpl -- PARAMS: " + logTarget.toString());
			targetJpaRepository.save(target);
			logTargetJpaRepository.save(logTarget);
		}
	
	}
	
	
}
