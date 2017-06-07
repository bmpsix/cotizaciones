package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogOperation;
import com.unimer.cotizaciones.entities.Operation;
import com.unimer.cotizaciones.repositories.LogOperationJpaRepository;
import com.unimer.cotizaciones.repositories.OperationJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.OperationService;

@Service("operationServiceImpl")
public class OperationServiceImpl implements OperationService {

	
	@Autowired
	@Qualifier("operationJpaRepository")
	private OperationJpaRepository operationJpaRepository;

	@Autowired
	@Qualifier("logOperationJpaRepository")
	private LogOperationJpaRepository logOperationJpaRepository;	
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	
	
	private static final Log LOG = LogFactory.getLog(OperationServiceImpl.class);
	

	@Override
	public void addOperation(Operation operation, int idUser) {
	

			if (operation.getIdOperation()==0) {
				
				operationJpaRepository.save(operation);
				LOG.info("METHOD: addOperation in OperationServiceImpl -- PARAMS: " + operation.toString());
			
			} else {
				 updateOperation(operation, idUser);
			}

		} 

	@Override
	public List<Operation> listAllOperation() {
		return operationJpaRepository.findAll();
	}

	@Override
	public Operation findById(int idOperation) {
		return operationJpaRepository.findByIdOperation(idOperation);
	}

	private void updateOperation(Operation operation, int idUser) {
		java.util.Date date = new Date();
		Operation operationToUpdate = operationJpaRepository.findByIdOperation(operation.getIdOperation());
		if (operationToUpdate != null) {
			LogOperation logOperation = new LogOperation(date, "Operation  modified", idUser, operationToUpdate.getDetail(),operationToUpdate.getIdOperation());
			operationJpaRepository.save(operation);
			logOperationJpaRepository.save(logOperation);
		
		}
	}

	@Override
	public Operation findByDetail(String detail) {
		return operationJpaRepository.findByDetail(detail);
	}
	
	
}
