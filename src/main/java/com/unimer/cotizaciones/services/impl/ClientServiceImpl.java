package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Client;
import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogClient;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ClientJpaRepository;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogClientJpaRepository;
import com.unimer.cotizaciones.services.ClientService;
import com.unimer.cotizaciones.services.TraceResponseService;



@Service("clientServiceImpl")
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	@Qualifier("clientJpaRepository")
	private ClientJpaRepository clientJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logClientJpaRepository")
	private LogClientJpaRepository logClientJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;

	private static final Log LOG = LogFactory.getLog(ClientServiceImpl.class);
	
	String ipCliente="";
	
	
	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Client");
	}

	@Override
	public Client addClient(Client client) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Client");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Client");
			consecutive.setPrefix("CLI");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for client");
			consecutivesJpaRepository.save(consecutive);
			client.setIdClient(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!client.getIdClient().equals(clientJpaRepository.findOne(client.getIdClient()))) {
				
				clientJpaRepository.save(client);
				LOG.info("METHOD: addClient in ClientServiceImpl -- PARAMS: " + client.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se actualizo un nuevo cliente");
			} else {
				 updateClient(client);
			}

		} else if (client.getIdClient() == null) {

			client.setIdClient(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!client.getIdClient().equals(clientJpaRepository.findOne(client.getIdClient()))) {
				LOG.info("METHOD: addClient in clientServiceImpl -- PARAMS: " + client.toString());
				clientJpaRepository.save(client);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agrego un cliente");
			} else {
				 updateClient(client);
			}
		} else {
			 updateClient(client);
		}
		return client;
	}

	@Override
	public List<Client> listAllClient() {
		return clientJpaRepository.findAll();
	}

	@Override
	public Client findById(String idClient) {
		return clientJpaRepository.findOne(idClient);
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
			
			insertBinnacle("Se actualizo un cliente");
		}
	}
	
	@Override
	public void IP(String ip) {
		ipCliente=ip;
		
	}
	
	private void insertBinnacle(String msg)
	{
		Date date = new Date();
		TraceResponse traceResponse = new TraceResponse(null,"test",msg,ipCliente,date);
		traceResponseService.addTraceResponse(traceResponse);
	}



}
