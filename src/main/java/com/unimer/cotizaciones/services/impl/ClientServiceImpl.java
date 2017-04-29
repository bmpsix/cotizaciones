package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Client;
import com.unimer.cotizaciones.entities.LogClient;
import com.unimer.cotizaciones.repositories.ClientJpaRepository;
import com.unimer.cotizaciones.repositories.LogClientJpaRepository;
import com.unimer.cotizaciones.services.ClientService;



@Service("clientServiceImpl")
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	@Qualifier("clientJpaRepository")
	private ClientJpaRepository clientJpaRepository;

	@Autowired
	@Qualifier("logClientJpaRepository")
	private LogClientJpaRepository logClientJpaRepository;
	

	private static final Log LOG = LogFactory.getLog(ClientServiceImpl.class);
	

	@Override
	public void addClient(Client client) {
		
			if (client.getIdClient()==0) {
				
				clientJpaRepository.save(client);
				LOG.info("METHOD: addClient in ClientServiceImpl -- PARAMS: " + client.toString());
			
			} else {
				 updateClient(client);
			}

		} 

	@Override
	public List<Client> listAllClient() {
		return clientJpaRepository.findAll();
	}

	@Override
	public Client findById(int idClient) {
		return clientJpaRepository.findByIdClient(idClient);
	}
	
	@Override
	public List<Client> findByActiveStatus() {
		return clientJpaRepository.findByStatus((byte) 1);
	}
	
	private void updateClient(Client client) {
		java.util.Date date = new Date();
		Client clientToUpdate = clientJpaRepository.findByIdClient(client.getIdClient());
		if (clientToUpdate != null) {
			
			LogClient logClient = new LogClient(date, "Client  modified", "test", clientToUpdate.getDetail(), clientToUpdate.getEmail(),clientToUpdate.getFax(), clientToUpdate.getIdClient(),clientToUpdate.getClientType().getIdClientType(),clientToUpdate.getCountry().getIdCountry(),clientToUpdate.getSaClient().getIdSaClient(),clientToUpdate.getPhone(),clientToUpdate.getStatus());
			clientJpaRepository.save(client);
			logClientJpaRepository.save(logClient);
			
			
		}
	}
	
	
}
