package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.repositories.ClientTypeJpaRepository;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogClientTypeJpaRepository;
import com.unimer.cotizaciones.services.ClientTypeService;

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

	private static final Log LOG = LogFactory.getLog(ClientTypeServiceImpl.class);
	

	
	@Override
	public ClientTypeService addRol(ClientTypeService clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientTypeService> listAllClientType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatusById(String idClientType, byte status) {
		// TODO Auto-generated method stub

	}

	@Override
	public ClientTypeService findById(String idClientType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consecutive getConsecutive() {
		// TODO Auto-generated method stub
		return null;
	}

}
