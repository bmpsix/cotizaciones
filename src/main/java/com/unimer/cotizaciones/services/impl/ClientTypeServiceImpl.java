package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.ClientType;
import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogClientType;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ClientTypeJpaRepository;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogClientTypeJpaRepository;
import com.unimer.cotizaciones.services.ClientTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("clientTypeServiceImpl")
public class ClientTypeServiceImpl implements ClientTypeService {

	
	@Autowired
	@Qualifier("clientTypeJpaRepository")
	private ClientTypeJpaRepository clientTypeJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logClientTypeJpaRepository")
	private LogClientTypeJpaRepository logClientTypeJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;

	private static final Log LOG = LogFactory.getLog(ClientTypeServiceImpl.class);
	
	String ipCliente="";
	

	
	@Override
	public ClientType addClientType(ClientType clientType) {

		Consecutive consecutive = consecutivesJpaRepository.findByType("Client type");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Client type");
			consecutive.setPrefix("CTP");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for client type");
			consecutivesJpaRepository.save(consecutive);
			clientType.setIdClientType(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!clientType.getIdClientType().equals(clientTypeJpaRepository.findOne(clientType.getIdClientType()))) {
				
				clientTypeJpaRepository.save(clientType);
				LOG.info("METHOD: addClientType in ClientTypeServiceImpl -- PARAMS: " + clientType.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se actualizo un nuevo tipo de cliente");

			} else {
				updateClientType(clientType);
			}

		} else if (clientType.getIdClientType() == null) {

			clientType.setIdClientType(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!clientType.getIdClientType().equals(clientTypeJpaRepository.findOne(clientType.getIdClientType()))) {
				LOG.info("METHOD: addClientType in ClientTypeServiceImpl -- PARAMS: " + clientType.toString());
				clientTypeJpaRepository.save(clientType);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agrego un tipo de cliente");
			} else {
				updateClientType(clientType);
			}
		} else {
			updateClientType(clientType);
		}
		return clientType;
	}

	@Override
	public List<ClientType> listAllClientType() {
		
		return clientTypeJpaRepository.findAll();
	}

	@Override
	public void updateStatusById(String idClientType, byte status) {
		ClientType clientType = clientTypeJpaRepository.findOne(idClientType);
		if (clientType != null) {
			clientType.setStatus(status);
			clientTypeJpaRepository.save(clientType);
			
		}

	}

	@Override
	public ClientType findById(String idClientType) {
		return clientTypeJpaRepository.findByIdClientType(idClientType);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Client type");
	}

	private void updateClientType(ClientType clientType) {
		java.util.Date date = new Date();
		ClientType clientTypeToUpdate = clientTypeJpaRepository.findByIdClientType(clientType.getIdClientType());
		if (clientTypeToUpdate != null) {
			LogClientType logClientType = new LogClientType(date, "Client type  modified", "test", clientTypeToUpdate.getDetail(), clientTypeToUpdate.getIdClientType(),
					clientTypeToUpdate.getStatus());
			clientTypeJpaRepository.save(clientType);
			logClientTypeJpaRepository.save(logClientType);
			
			insertBinnacle("Se actualizo un tipo de cliente");
		}
	}

	@Override
	public List<ClientType> findByActiveStatus() {
		return clientTypeJpaRepository.findByStatus((byte) 1);
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
