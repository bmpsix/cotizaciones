package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.CountryByCurrencyType;
import com.unimer.cotizaciones.repositories.CountryByCurrencyTypeJpaRepository;
import com.unimer.cotizaciones.repositories.LogCountryByCurrencyTypeJpaRepository;
import com.unimer.cotizaciones.services.CountryByCurrencyTypeService;


@Service("countryByCurrencyTypeServiceImpl")
public class CountryByCurrencyTypeServiceImpl implements CountryByCurrencyTypeService {

	
	@Autowired
	@Qualifier("countryByCurrencyTypeJpaRepository")
	private CountryByCurrencyTypeJpaRepository countryByCurrencyTypeJpaRepository;

	@Autowired
	@Qualifier("logCountryByCurrencyTypeJpaRepository")
	private LogCountryByCurrencyTypeJpaRepository logCountryByCurrencyTypeJpaRepository;
	
	
	private static final Log LOG = LogFactory.getLog(CountryByCurrencyTypeServiceImpl.class);
	
	@Override
	public List<CountryByCurrencyType> listAllCountryByCurrencyType() {
		
		return countryByCurrencyTypeJpaRepository.findAll();
	}


	@Override
	public void addCountryByCurrencyType(CountryByCurrencyType countryByCurrencyType) {
		LOG.info("METHOD: addCountryByCurrencyType in CountryByCurrencyTypeServiceImpl -- PARAMS: " + countryByCurrencyType.toString());
		countryByCurrencyTypeJpaRepository.save(countryByCurrencyType);
	}



	
	

}
