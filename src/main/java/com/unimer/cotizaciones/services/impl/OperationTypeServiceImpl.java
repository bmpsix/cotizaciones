package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogOperationType;
import com.unimer.cotizaciones.entities.OperationType;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogOperationTypeJpaRepository;
import com.unimer.cotizaciones.repositories.OperationTypeJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.OperationTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("operationTypeServiceImpl")
public class OperationTypeServiceImpl implements OperationTypeService {

	
	@Autowired
	@Qualifier("operationTypeJpaRepository")
	private OperationTypeJpaRepository operationTypeJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logOperationTypeJpaRepository")
	private LogOperationTypeJpaRepository logOperationTypeJpaRepository;

	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	String ipCliente="";
	
	private static final Log LOG = LogFactory.getLog(OperationTypeServiceImpl.class);
	
	
	@Override
	public OperationType addOperationType(OperationType operationType) {
		
		Consecutive consecutive = consecutivesJpaRepository.findByType("Operation type");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Operation type");
			consecutive.setPrefix("OPT");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for Operation type");
			consecutivesJpaRepository.save(consecutive);
			operationType.setIdOperationType(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!operationType.getIdOperationType().equals(operationTypeJpaRepository.findOne(operationType.getIdOperationType()))) {
				
				operationTypeJpaRepository.save(operationType);
				LOG.info("METHOD: addOperationType in OperationTypeServiceImpl -- PARAMS: " + operationType.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó un tipo de operación");

			} else {
				updateOperationType(operationType);
			}

		} else if (operationType.getIdOperationType() == null) {

			operationType.setIdOperationType(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!operationType.getIdOperationType().equals(operationTypeJpaRepository.findOne(operationType.getIdOperationType()))) {
				LOG.info("METHOD: addOperationType in OperationTypeServiceImpl -- PARAMS: " + operationType.toString());
				operationTypeJpaRepository.save(operationType);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó un tipo de operación");
			} else {
				updateOperationType(operationType);
			}
		} else {
			updateOperationType(operationType);
		}
		return operationType;
	}

	@Override
	public List<OperationType> listAllOperationType() {
		return operationTypeJpaRepository.findAll();
	}

	@Override
	public OperationType findById(String idOperationType) {
		return operationTypeJpaRepository.findByIdOperationType(idOperationType);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Operation type");
	}
	
	private void updateOperationType(OperationType operationType) {
		java.util.Date date = new Date();
		OperationType operationTypeToUpdate = operationTypeJpaRepository.findByIdOperationType(operationType.getIdOperationType());
		if (operationTypeToUpdate != null) {
			LogOperationType logOperationType = new LogOperationType(date, "Operation type  modified", "test", operationTypeToUpdate.getDetail(), operationTypeToUpdate.getIdOperationType());
			operationTypeJpaRepository.save(operationType);
			logOperationTypeJpaRepository.save(logOperationType);
			insertBinnacle("Se actualizó una operación");
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
