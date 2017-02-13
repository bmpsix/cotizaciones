package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogSaClient;
import com.unimer.cotizaciones.entities.SaClient;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogSaClientJpaRepository;
import com.unimer.cotizaciones.repositories.SaClientJpaRepository;
import com.unimer.cotizaciones.services.SaClientService;

@Service("saClientServiceImpl")
public class SaClientServiceImpl implements SaClientService {

	
	@Autowired
	@Qualifier("saClientJpaRepository")
	private SaClientJpaRepository saClientJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logSaClientJpaRepository")
	private LogSaClientJpaRepository logSaClientJpaRepository;

	private static final Log LOG = LogFactory.getLog(SaClientServiceImpl.class);
	
	
	
	
	@Override
	public SaClient addSaClient(SaClient saClient) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("SA client");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("SA client");
			consecutive.setPrefix("SAC");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for SA client");
			consecutivesJpaRepository.save(consecutive);
			saClient.setIdSaClient(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!saClient.getIdSaClient().equals(saClientJpaRepository.findOne(saClient.getIdSaClient()))) {
				
				saClientJpaRepository.save(saClient);
				LOG.info("METHOD: addSaClient in SaClientServiceImpl -- PARAMS: " + saClient.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				updateSaClient(saClient);
			}

		} else if (saClient.getIdSaClient() == null) {

			saClient.setIdSaClient(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!saClient.getIdSaClient().equals(saClientJpaRepository.findOne(saClient.getIdSaClient()))) {
				LOG.info("METHOD: addRol in SaClientServiceImpl -- PARAMS: " + saClient.toString());
				saClientJpaRepository.save(saClient);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateSaClient(saClient);;
			}
		} else {
			updateSaClient(saClient);
		}
		return saClient;
	}

	@Override
	public List<SaClient> listAllSaClient() {
		return saClientJpaRepository.findAll();
	}

	@Override
	public void updateStatusById(String idSaClient, byte status) {
		SaClient saClient = saClientJpaRepository.findOne(idSaClient);

		if (saClient != null) {
			saClient.setStatus(status);
			saClientJpaRepository.save(saClient);
		}

	}

	@Override
	public SaClient findById(String idSaClient) {
		return saClientJpaRepository.findByIdSaClient(idSaClient);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("SA client");
	}
	
	private void updateSaClient(SaClient saClient) {
		java.util.Date date = new Date();
		SaClient saClientToUpdate = saClientJpaRepository.findByIdSaClient(saClient.getIdSaClient());
		if (saClientToUpdate != null) {
			LogSaClient logSaClient = new LogSaClient(date, "SA client  modified", "test", saClientToUpdate.getDetail(), saClientToUpdate.getIdSaClient(),
					saClientToUpdate.getStatus());
			saClientJpaRepository.save(saClient);
			logSaClientJpaRepository.save(logSaClient);
		}
	}

}
