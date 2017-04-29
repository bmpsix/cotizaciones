package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogOperationType;
import com.unimer.cotizaciones.entities.OperationType;
import com.unimer.cotizaciones.repositories.LogOperationTypeJpaRepository;
import com.unimer.cotizaciones.repositories.OperationTypeJpaRepository;
import com.unimer.cotizaciones.services.OperationTypeService;

@Service("operationTypeServiceImpl")
public class OperationTypeServiceImpl implements OperationTypeService {

	
	@Autowired
	@Qualifier("operationTypeJpaRepository")
	private OperationTypeJpaRepository operationTypeJpaRepository;

	
	@Autowired
	@Qualifier("logOperationTypeJpaRepository")
	private LogOperationTypeJpaRepository logOperationTypeJpaRepository;

	
	private static final Log LOG = LogFactory.getLog(OperationTypeServiceImpl.class);
	
	
	@Override
	public void addOperationType(OperationType operationType) {

			if (operationType.getIdOperationType()==0) {
				
				operationTypeJpaRepository.save(operationType);
				LOG.info("METHOD: addOperationType in OperationTypeServiceImpl -- PARAMS: " + operationType.toString());
				

			} else {
				updateOperationType(operationType);
			}

		}
	
	@Override
	public List<OperationType> listAllOperationType() {
		return operationTypeJpaRepository.findAll();
	}

	@Override
	public OperationType findById(int idOperationType) {
		return operationTypeJpaRepository.findByIdOperationType(idOperationType);
	}


	
	private void updateOperationType(OperationType operationType) {
		java.util.Date date = new Date();
		OperationType operationTypeToUpdate = operationTypeJpaRepository.findByIdOperationType(operationType.getIdOperationType());
		if (operationTypeToUpdate != null) {
			LogOperationType logOperationType = new LogOperationType(date, "Operation type  modified", "test", operationTypeToUpdate.getDetail(), operationTypeToUpdate.getIdOperationType());
			operationTypeJpaRepository.save(operationType);
			logOperationTypeJpaRepository.save(logOperationType);
			
		}
	}
	
	
	@Override
	public OperationType findByDetail(String detail) {
		return operationTypeJpaRepository.findByDetail(detail);
	}

}
