package com.unimer.cotizaciones.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogTarget;
import com.unimer.cotizaciones.entities.Target;
import com.unimer.cotizaciones.services.TargetService;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogTargetJpaRepository;
import com.unimer.cotizaciones.repositories.TargetJpaRepository;

@Service("targetServiceImpl")
public class TargetServiceImpl implements TargetService {
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	@Autowired
	@Qualifier("targetJpaRepository")
	private TargetJpaRepository targetJpaRepository;
	@Autowired
	@Qualifier("logTargetJpaRepository")
	private LogTargetJpaRepository logTargetJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(TargetServiceImpl.class);
	
	@Override
	public Consecutive getConsecutive() {
		
		return consecutivesJpaRepository.findByType("Target");
	}	

	@Override
	public Target addTarget(Target target) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Target");
		
		if(consecutive == null)
		{
			consecutive = new Consecutive();
			consecutive.setType("Target");
			consecutive.setPrefix("TRT");
			consecutive.setDetail("Default consecutive for target table");
			consecutivesJpaRepository.save(consecutive);
			target.setIdTarget(consecutive.getPrefix()+ "-" + consecutive.getSubfix());
			Date date= new Date();			
			SimpleDateFormat spl=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d=spl.format(date);
			
			
		if(!target.getIdTarget().equals(targetJpaRepository.findOne(target.getIdTarget())))
		{
			targetJpaRepository.save(target);
			LOG.info("METHOD: addTarget -- PARAMS: " + target.toString());
			targetJpaRepository.save(target);
			consecutive.setSubfix(consecutive.getSubfix() + 1);
			consecutivesJpaRepository.save(consecutive);
			
		} else
		{
			updateTarget(target);
		}	
		}else
		{
			updateTarget(target);
		}
		return target;
	}

	@Override
	public List<Target> listAllTargets() {
	
		return targetJpaRepository.findAll();
	}

	@Override
	public Target findById(String idTarget) {
	
		return targetJpaRepository.findOne(idTarget);
	}

	private void updateTarget(Target target)
	
	{
		//Date date = new Date();
		Target targetToUpdate = targetJpaRepository.findByIdTarget(target.getIdTarget());
		if (targetToUpdate != null)
		{
			LogTarget logTarget = new LogTarget();
			targetJpaRepository.save(target);
			logTargetJpaRepository.save(logTarget);
		}
	}
	
	
	
}
