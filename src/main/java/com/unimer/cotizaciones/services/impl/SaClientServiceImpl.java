package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogSaClient;
import com.unimer.cotizaciones.entities.SaClient;
import com.unimer.cotizaciones.repositories.LogSaClientJpaRepository;
import com.unimer.cotizaciones.repositories.SaClientJpaRepository;
import com.unimer.cotizaciones.services.SaClientService;

@Service("saClientServiceImpl")
public class SaClientServiceImpl implements SaClientService {

	
	@Autowired
	@Qualifier("saClientJpaRepository")
	private SaClientJpaRepository saClientJpaRepository;


	@Autowired
	@Qualifier("logSaClientJpaRepository")
	private LogSaClientJpaRepository logSaClientJpaRepository;

	
	private static final Log LOG = LogFactory.getLog(SaClientServiceImpl.class);
	
	@Override
	public void addSaClient(SaClient saClient, int idUser) {
		
			if (saClient.getIdSaClient()==0) {
				
				saClientJpaRepository.save(saClient);
				LOG.info("METHOD: addSaClient in SaClientServiceImpl -- PARAMS: " + saClient.toString());
				

			} else {
				updateSaClient(saClient,idUser);
			}

		}

	@Override
	public List<SaClient> listAllSaClient() {
		return saClientJpaRepository.findAll();
	}


	@Override
	public SaClient findById(int idSaClient) {
		return saClientJpaRepository.findByIdSaClient(idSaClient);
	}

	
	private void updateSaClient(SaClient saClient, int idUser) {
		java.util.Date date = new Date();
		SaClient saClientToUpdate = saClientJpaRepository.findByIdSaClient(saClient.getIdSaClient());
		if (saClientToUpdate != null) {
			LogSaClient logSaClient = new LogSaClient(date, "SA client  modified", idUser, saClientToUpdate.getDetail(), saClientToUpdate.getIdSaClient(),
					saClientToUpdate.getStatus());
			saClientJpaRepository.save(saClient);
			logSaClientJpaRepository.save(logSaClient);
			
		}
	}

	@Override
	public List<SaClient> findByActiveStatus() {
		return saClientJpaRepository.findByStatus((byte) 1);
	}

}
