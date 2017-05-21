package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.ClientContact;
import com.unimer.cotizaciones.entities.LogClientContact;
import com.unimer.cotizaciones.repositories.ClientContactJpaRepository;
import com.unimer.cotizaciones.repositories.ClientJpaRepository;
import com.unimer.cotizaciones.repositories.CountryJpaRepository;
import com.unimer.cotizaciones.repositories.LogClientContactJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.ClientContactService;

@Service("clientContactServiceImpl")
public class ClientContactServiceImpl implements ClientContactService {

	
	@Autowired
	@Qualifier("clientContactJpaRepository")
	private ClientContactJpaRepository clientContactJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;

	
	@Autowired
	@Qualifier("logClientContactJpaRepository")
	private LogClientContactJpaRepository logClientContactJpaRepository;
	
	@Autowired
	@Qualifier("clientJpaRepository")
	private ClientJpaRepository clientJpaRepository;
	
	@Autowired
	@Qualifier("countryJpaRepository")
	private CountryJpaRepository countryJpaRepository;
	
	
	private static final Log LOG = LogFactory.getLog(ClientContactServiceImpl.class);
	
	@Override
	public void addClientContact(ClientContact clientContact, int idUser) {
		
			if (clientContact.getIdClientContact()==0) {
				
				
				clientContactJpaRepository.save(clientContact);
				LOG.info("METHOD: addClientContact in ClientContactServiceImpl -- PARAMS: " + clientContact.toString());
		

			} else {
				updateClientContact(clientContact, idUser);
			}

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
	public ClientContact findById(int idClientContact) {
		return clientContactJpaRepository.findByIdClientContact(idClientContact);
	}

	

	private void updateClientContact(ClientContact clientContact, int idUser) {
		java.util.Date date = new Date();
		ClientContact clientContactToUpdate = clientContactJpaRepository.findByIdClientContact(clientContact.getIdClientContact());
		if (clientContactToUpdate != null) {
			LogClientContact logClientContact = new LogClientContact(date, "Client contact  modified", idUser, clientContactToUpdate.getEmail(),clientContactToUpdate.getExt(),
					clientContactToUpdate.getClient().getIdClient(),clientContactToUpdate.getIdClientContact(),clientContactToUpdate.getCountry().getIdCountry(),clientContactToUpdate.getName(),
					clientContactToUpdate.getPhone(),clientContactToUpdate.getStatus());
			clientContactJpaRepository.save(clientContact);
			logClientContactJpaRepository.save(logClientContact);
			
		}
	}

	
	

	
}
