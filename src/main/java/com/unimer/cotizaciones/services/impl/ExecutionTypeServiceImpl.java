package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.ExecutionType;
import com.unimer.cotizaciones.entities.LogExecutionType;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.ExecutionTypeJpaRepository;
import com.unimer.cotizaciones.repositories.LogExecutionTypeJpaRepository;
import com.unimer.cotizaciones.services.ExecutionTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;



@Service("executionTypeServiceImpl")
public class ExecutionTypeServiceImpl implements ExecutionTypeService {

	
	@Autowired
	@Qualifier("executionTypeJpaRepository")
	private ExecutionTypeJpaRepository executionTypeJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logExecutionTypeJpaRepository")
	private LogExecutionTypeJpaRepository logRolJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	private static final Log LOG = LogFactory.getLog(ExecutionTypeServiceImpl.class);
	
	String ipCliente="";
	
	@Override
	public ExecutionType addExecutionType(ExecutionType executionType) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Execution type");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Execution type");
			consecutive.setPrefix("EXT");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for execution type");
			consecutivesJpaRepository.save(consecutive);
			executionType.setIdExecutionType(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!executionType.getIdExecutionType().equals(executionTypeJpaRepository.findOne(executionType.getIdExecutionType()))) {
				
				executionTypeJpaRepository.save(executionType);
				LOG.info("METHOD: addExecutionType in ExecutionTypeServiceImpl -- PARAMS: " + executionType.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se actualizo un tipo de ejecucion");
			} else {
				updateExecutionType(executionType);
			}

		} else if (executionType.getIdExecutionType() == null) {

			executionType.setIdExecutionType(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!executionType.getIdExecutionType().equals(executionTypeJpaRepository.findOne(executionType.getIdExecutionType()))) {
				LOG.info("METHOD: addExecutionType in ExecutionTypeServiceImpl -- PARAMS: " + executionType.toString());
				executionTypeJpaRepository.save(executionType);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agrego un tipo de ejecucion");
			} else {
				updateExecutionType(executionType);
			}
		} else {
			updateExecutionType(executionType);
		}
		return executionType;
	}

	@Override
	public List<ExecutionType> listAllExecutionType() {
		return executionTypeJpaRepository.findAll();
	}

	@Override
	public ExecutionType findById(String idExecutionType) {
		return executionTypeJpaRepository.findByIdExecutionType(idExecutionType);
	}

	
	/*@Override
	public void updateStatusById(String idExecutionType, byte status) {

		ExecutionType executionType = executionTypeJpaRepository.findOne(idExecutionType);

		if (executionType != null) {
			executionType.setStatus(status);
			executionTypeJpaRepository.save(executionType);
		}
	}*/

	
	
	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Execution type");
	}

	private void updateExecutionType(ExecutionType executionType) {
		java.util.Date date = new Date();
		ExecutionType executionTypeToUpdate = executionTypeJpaRepository.findByIdExecutionType(executionType.getIdExecutionType());
		if (executionTypeToUpdate != null) {
			LogExecutionType logExecutionType = new LogExecutionType(date, "Execution type  modified", "test", executionTypeToUpdate.getDetail(), executionTypeToUpdate.getIdExecutionType());
			executionTypeJpaRepository.save(executionType);
			logRolJpaRepository.save(logExecutionType);
			
			insertBinnacle("Se actualizo un tipo de ejecucion");
		}
	}
	
	@Override
	public void IP(String ip) {
		ipCliente=ip;
		
	}
	
	private void insertBinnacle(String msg)
	{
		TraceResponse traceResponse = new TraceResponse(null,"test",msg,ipCliente);
		traceResponseService.addTraceResponse(traceResponse);
	}

}
