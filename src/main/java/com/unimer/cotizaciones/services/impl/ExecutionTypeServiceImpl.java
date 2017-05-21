package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.ExecutionType;
import com.unimer.cotizaciones.entities.LogExecutionType;
import com.unimer.cotizaciones.repositories.ExecutionTypeJpaRepository;
import com.unimer.cotizaciones.repositories.LogExecutionTypeJpaRepository;
import com.unimer.cotizaciones.services.ExecutionTypeService;



@Service("executionTypeServiceImpl")
public class ExecutionTypeServiceImpl implements ExecutionTypeService {

	
	@Autowired
	@Qualifier("executionTypeJpaRepository")
	private ExecutionTypeJpaRepository executionTypeJpaRepository;

	@Autowired
	@Qualifier("logExecutionTypeJpaRepository")
	private LogExecutionTypeJpaRepository logRolJpaRepository;
	

	
	private static final Log LOG = LogFactory.getLog(ExecutionTypeServiceImpl.class);
	
	String ipCliente="";
	
	@Override
	public void addExecutionType(ExecutionType executionType, int idUser) {
	
			if (executionType.getIdExecutionType()==0) {
				
				executionTypeJpaRepository.save(executionType);
				LOG.info("METHOD: addExecutionType in ExecutionTypeServiceImpl -- PARAMS: " + executionType.toString());
	
			} else {
				updateExecutionType(executionType, idUser);
			}

		} 

	@Override
	public List<ExecutionType> listAllExecutionType() {
		return executionTypeJpaRepository.findAll();
	}

	@Override
	public ExecutionType findById(int idExecutionType) {
		return executionTypeJpaRepository.findByIdExecutionType(idExecutionType);
	}

	
	
	

	private void updateExecutionType(ExecutionType executionType, int idUser) {
		java.util.Date date = new Date();
		ExecutionType executionTypeToUpdate = executionTypeJpaRepository.findByIdExecutionType(executionType.getIdExecutionType());
		if (executionTypeToUpdate != null) {
			LogExecutionType logExecutionType = new LogExecutionType(date, "Execution type  modified", idUser, executionTypeToUpdate.getDetail(), executionTypeToUpdate.getIdExecutionType());
			executionTypeJpaRepository.save(executionType);
			logRolJpaRepository.save(logExecutionType);
		}
	}
	


}
