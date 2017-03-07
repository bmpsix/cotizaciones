package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.ClientContact;
import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogClientContact;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ClientContactJpaRepository;
import com.unimer.cotizaciones.repositories.ClientJpaRepository;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.CountryJpaRepository;
import com.unimer.cotizaciones.repositories.LogClientContactJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.ClientContactService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("clientContactServiceImpl")
public class ClientContactServiceImpl implements ClientContactService {

	
	@Autowired
	@Qualifier("clientContactJpaRepository")
	private ClientContactJpaRepository clientContactJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logClientContactJpaRepository")
	private LogClientContactJpaRepository logClientContactJpaRepository;
	
	@Autowired
	@Qualifier("clientJpaRepository")
	private ClientJpaRepository clientJpaRepository;
	
	@Autowired
	@Qualifier("countryJpaRepository")
	private CountryJpaRepository countryJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	
	String ipCliente="";
	
	private static final Log LOG = LogFactory.getLog(ClientContactServiceImpl.class);
	
	@Override
	public ClientContact addClientContact(ClientContact clientContact) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Client contact");
		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Client contact");
			consecutive.setPrefix("CLC");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for Client contact");
			consecutivesJpaRepository.save(consecutive);
			clientContact.setIdClientContact(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!clientContact.getIdClientContact().equals(clientContactJpaRepository.findOne(clientContact.getIdClientContact()))) {
				
				
				clientContactJpaRepository.save(clientContact);
				LOG.info("METHOD: addClientContact in ClientContactServiceImpl -- PARAMS: " + clientContact.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó un nuevo contacto de cliente");

			} else {
				updateClientContact(clientContact);
			}

		} else if (clientContact.getIdClientContact() == null) {

			clientContact.setIdClientContact(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!clientContact.getIdClientContact().equals(clientContactJpaRepository.findOne(clientContact.getIdClientContact()))) {
				LOG.info("METHOD: addClientContact in ClientContactServiceImpl -- PARAMS: " + clientContact.toString());
				clientContactJpaRepository.save(clientContact);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				
				insertBinnacle("Se actualizo un nuevo contacto de cliente");
			} else {
				updateClientContact(clientContact);
			}
		} else {
			updateClientContact(clientContact);
		}
		return clientContact;
	}

	@Override
	public List<ClientContact> listAllClientContact() {
		return clientContactJpaRepository.findAll();
	}

	@Override
	public List<ClientContact> findByActiveStatus() {
		return clientContactJpaRepository.findByStatus((byte) 1);
	}

	@Override
	public ClientContact findById(String idClientContact) {
		return clientContactJpaRepository.findByIdClientContact(idClientContact);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Client contact");
	}

	private void updateClientContact(ClientContact clientContact) {
		java.util.Date date = new Date();
		ClientContact clientContactToUpdate = clientContactJpaRepository.findByIdClientContact(clientContact.getIdClientContact());
		if (clientContactToUpdate != null) {
			LogClientContact logClientContact = new LogClientContact(date, "Client contact  modified", "test", clientContactToUpdate.getEmail(),clientContactToUpdate.getExt(),
					clientContactToUpdate.getClient().getIdClient(),clientContactToUpdate.getIdClientContact(),clientContactToUpdate.getCountry().getIdCountry(),clientContactToUpdate.getName(),
					clientContactToUpdate.getPhone(),clientContactToUpdate.getStatus());
			clientContactJpaRepository.save(clientContact);
			logClientContactJpaRepository.save(logClientContact);
			
			insertBinnacle("Se actualizó un contacto de cliente");
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
