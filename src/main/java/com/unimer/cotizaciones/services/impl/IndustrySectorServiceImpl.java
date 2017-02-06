package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.IndustrySector;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.IndustrySectorJpaRepository;
import com.unimer.cotizaciones.repositories.LogIndustrySectorJpaRepository;
import com.unimer.cotizaciones.services.IndustrySectorService;

@Service("industrySectorImpl")
public class IndustrySectorServiceImpl implements IndustrySectorService{

	@Autowired
	@Qualifier("industrySectorJpaRepository")
	private IndustrySectorJpaRepository industrySectorJpaRepository; 
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logIndustrySectorJpaRepository")
	private LogIndustrySectorJpaRepository logIndustrySectorJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(CountryServiceImpl.class);
	
	@Override
	public List<IndustrySector> listAllIndustrySectors() {
		
		return null;
		
	}

	@Override
	public IndustrySector findById(String idIndustrySector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addIndustrySector(IndustrySector industrySector) {
		// TODO Auto-generated method stub
		
	}
	
	

}
