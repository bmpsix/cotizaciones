package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogOperation;
import com.unimer.cotizaciones.entities.Operation;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogOperationJpaRepository;
import com.unimer.cotizaciones.repositories.OperationJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.OperationService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("operationServiceImpl")
public class OperationServiceImpl implements OperationService {

	
	@Autowired
	@Qualifier("operationJpaRepository")
	private OperationJpaRepository operationJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logOperationJpaRepository")
	private LogOperationJpaRepository logOperationJpaRepository;	
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	String ipCliente="";
	
	private static final Log LOG = LogFactory.getLog(OperationServiceImpl.class);
	
	
	
	
	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Operation");
	}

	@Override
	public Operation addOperation(Operation operation) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Operation");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Operation");
			consecutive.setPrefix("OPE");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for Operation");
			consecutivesJpaRepository.save(consecutive);
			operation.setIdOperation(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!operation.getIdOperation().equals(operationJpaRepository.findOne(operation.getIdOperation()))) {
				
				operationJpaRepository.save(operation);
				LOG.info("METHOD: addOperation in OperationServiceImpl -- PARAMS: " + operation.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó una operación");

			} else {
				 updateOperation(operation);
			}

		} else if (operation.getIdOperation() == null) {

			operation.setIdOperation(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!operation.getIdOperation().equals(operationJpaRepository.findOne(operation.getIdOperation()))) {
				LOG.info("METHOD: addOperation in OperationServiceImpl -- PARAMS: " + operation.toString());
				operationJpaRepository.save(operation);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó una operación");
			} else {
				updateOperation(operation);
			}
		} else {
			updateOperation(operation);
		}
		return operation;
	}

	@Override
	public List<Operation> listAllOperation() {
		return operationJpaRepository.findAll();
	}

	@Override
	public Operation findById(String idOperation) {
		return operationJpaRepository.findOne(idOperation);
	}

	private void updateOperation(Operation operation) {
		java.util.Date date = new Date();
		Operation operationToUpdate = operationJpaRepository.findByIdOperation(operation.getIdOperation());
		if (operationToUpdate != null) {
			LogOperation logOperation = new LogOperation(date, "Operation  modified", "test", operationToUpdate.getDetail(),operationToUpdate.getIdOperation());
			operationJpaRepository.save(operation);
			logOperationJpaRepository.save(logOperation);
			insertBinnacle("Se actualizó una operación");
		}
	}

	@Override
	public void IP(String ip) {
		ipCliente=ip;
		
	}
	
	private void insertBinnacle(String msg)
	{
		Date date = new Date();
		TraceResponse traceResponse = new TraceResponse(null,"test",msg,ipCliente, date);
		traceResponseService.addTraceResponse(traceResponse);
	}
	
	
}
