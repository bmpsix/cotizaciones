package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.IndustrySector;
import com.unimer.cotizaciones.entities.LogIndustrySector;
import com.unimer.cotizaciones.repositories.IndustrySectorJpaRepository;
import com.unimer.cotizaciones.repositories.LogIndustrySectorJpaRepository;
import com.unimer.cotizaciones.services.IndustrySectorService;

@Service("industrySectorServiceImpl")
public class IndustrySectorServiceImpl implements IndustrySectorService{


	@Autowired
	@Qualifier("industrySectorJpaRepository")
	private IndustrySectorJpaRepository industrySectorJpaRepository;

	@Autowired
	@Qualifier("logIndustrySectorJpaRepository")
	private LogIndustrySectorJpaRepository logIndustrySectorJpaRepository;


	private static final Log LOG = LogFactory.getLog(IndustrySectorServiceImpl.class);
	
	String ipCliente="";
	
	@Override
	public void addIndustrySector(IndustrySector industrySector, int idUser) {


			if (industrySector.getIdIndustrySector()==0) {
				
				industrySectorJpaRepository.save(industrySector);
				LOG.info("METHOD: addIndustrySector in IndustrySectorServiceImpl -- PARAMS: " + industrySector.toString());
				
			} else {
				updateIndustrySector(industrySector, idUser);
			}

		}

	
	@Override
	public List<IndustrySector> listAllIndustrySectors() {
		return industrySectorJpaRepository.findAll();
	}	

	@Override
	public IndustrySector findById(int idIndustrySector) {
		return industrySectorJpaRepository.findByIdIndustrySector(idIndustrySector);
	}

	private void updateIndustrySector(IndustrySector industrySector, int idUser) {
		java.util.Date date = new Date();
		IndustrySector industrySectorToUpdate = industrySectorJpaRepository.findByIdIndustrySector(industrySector.getIdIndustrySector());
		if (industrySectorToUpdate != null) {
			LogIndustrySector logIndustrySector = new LogIndustrySector(date, "IndustrySector  modified", idUser, industrySectorToUpdate.getDetail(), industrySectorToUpdate.getIdIndustrySector());
			industrySectorJpaRepository.save(industrySector);
			logIndustrySectorJpaRepository.save(logIndustrySector);
			
			
		}
	}
	


	@Override
	public long rowCount() {
		
		return industrySectorJpaRepository.count();
	}

}
