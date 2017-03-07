package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.Target;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.TargetService;
import com.unimer.cotizaciones.services.TraceResponseService;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogTargetJpaRepository;
import com.unimer.cotizaciones.repositories.TargetJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;

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
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	String ipCliente="";
	
	private static final Log LOG = LogFactory.getLog(TargetServiceImpl.class);
	
	
	
	@Override
	public Consecutive getConsecutive() {
		
		return consecutivesJpaRepository.findByType("Target");
	}	

	@Override
	public Target addTarget(Target target) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Target");
		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("target");
			consecutive.setPrefix("TRT");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of target table");
			consecutivesJpaRepository.save(consecutive);
			target.setIdTarget(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!target.getIdTarget().equals(targetJpaRepository.findOne(target.getIdTarget()))) {
				
				targetJpaRepository.save(target);
				LOG.info("METHOD: addtarget in targetServiceImpl -- PARAMS: " + target.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

				insertBinnacle("Se agregó un muestreo");

			} else {
				updateTarget(target);
			}

		} else if (target.getIdTarget() == null) {

			target.setIdTarget(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!target.getIdTarget().equals(targetJpaRepository.findOne(target.getIdTarget()))) {
				LOG.info("METHOD: addtarget in targetServiceImpl -- PARAMS: " + target.toString());
				targetJpaRepository.save(target);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó un muestreo");
				
			} else {
				
				updateTarget(target);
			}
		} else {
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
			//LogTarget logTarget = new LogTarget();
			//LOG.info("METHOD: addtarget in targetServiceImpl -- PARAMS: " + logTarget.toString());
			targetJpaRepository.save(target);
			//logTargetJpaRepository.save(logTarget);

			insertBinnacle("Se actualizó un muestreo");
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
