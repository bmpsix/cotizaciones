package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.ClientType;
import com.unimer.cotizaciones.entities.LogClientType;
import com.unimer.cotizaciones.repositories.ClientTypeJpaRepository;
import com.unimer.cotizaciones.repositories.LogClientTypeJpaRepository;
import com.unimer.cotizaciones.services.ClientTypeService;
@Service("clientTypeServiceImpl")
public class ClientTypeServiceImpl implements ClientTypeService {

	
	@Autowired
	@Qualifier("clientTypeJpaRepository")
	private ClientTypeJpaRepository clientTypeJpaRepository;

	@Autowired
	@Qualifier("logClientTypeJpaRepository")
	private LogClientTypeJpaRepository logClientTypeJpaRepository;


	private static final Log LOG = LogFactory.getLog(ClientTypeServiceImpl.class);
	
	
	@Override
	public void addClientType(ClientType clientType, int idUser) {

	
			if (clientType.getIdClientType()==0) {
				
				clientTypeJpaRepository.save(clientType);
				LOG.info("METHOD: addClientType in ClientTypeServiceImpl -- PARAMS: " + clientType.toString());

			} else {
				updateClientType(clientType, idUser);
			}

		} 

	@Override
	public List<ClientType> listAllClientType() {
		
		return clientTypeJpaRepository.findAll();
	}

	@Override
	public void updateStatusById(int idClientType, byte status) {
		ClientType clientType = clientTypeJpaRepository.findByIdClientType(idClientType);
		if (clientType != null) {
			clientType.setStatus(status);
			clientTypeJpaRepository.save(clientType);
			
		}

	}

	@Override
	public ClientType findById(int idClientType) {
		return clientTypeJpaRepository.findByIdClientType(idClientType);
	}

	
	private void updateClientType(ClientType clientType, int idUser) {
		java.util.Date date = new Date();
		ClientType clientTypeToUpdate = clientTypeJpaRepository.findByIdClientType(clientType.getIdClientType());
		if (clientTypeToUpdate != null) {
			LogClientType logClientType = new LogClientType(date, "Client type  modified", idUser, clientTypeToUpdate.getDetail(), clientTypeToUpdate.getIdClientType(),
					clientTypeToUpdate.getStatus());
			clientTypeJpaRepository.save(clientType);
			logClientTypeJpaRepository.save(logClientType);
			
		}
	}

	@Override
	public List<ClientType> findByActiveStatus() {
		return clientTypeJpaRepository.findByStatus((byte) 1);
	}
	
	
}
