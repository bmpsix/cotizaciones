package com.unimer.cotizaciones.services.impl;


import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.repositories.CountryJpaRepository;
import com.unimer.cotizaciones.repositories.CurrencyTypeJpaRepository;
import com.unimer.cotizaciones.repositories.LogCountryByCurrencyTypeJpaRepository;
import com.unimer.cotizaciones.services.CountryByCurrencyTypeService;


@Service("countryByCurrencyTypeServiceImpl")
public class CountryByCurrencyTypeServiceImpl implements CountryByCurrencyTypeService {

	
	@Autowired
	@Qualifier("countryJpaRepository")
	private CountryJpaRepository countryJpaRepository;

	@Autowired
	@Qualifier("currencyTypeJpaRepository")
	private CurrencyTypeJpaRepository currencyTypeJpaRepository;

	
	@Autowired
	@Qualifier("logCountryByCurrencyTypeJpaRepository")
	private LogCountryByCurrencyTypeJpaRepository logCountryByCurrencyTypeJpaRepository;
	
	
	
	
	private static final Log LOG = LogFactory.getLog(CountryByCurrencyTypeServiceImpl.class);
	
	
	@Override
	public List<Country> listAllCountryByCurrencyType() {
		return countryJpaRepository.findAll();
	}


	@Override
	public void addCountryByCurrencyType(int idCountry,int idCurrencyType) {
		LOG.info("METHOD: addCountryByCurrencyType in CountryByCurrencyTypeServiceImpl -- PARAMS: idCountry: "+idCountry+" idCurrencyType: "+idCurrencyType);
		Country country = countryJpaRepository.findByIdCountry(idCountry);
		CurrencyType currencyType= currencyTypeJpaRepository.findByIdCurrencyType(idCurrencyType);
		country.setCurrencyType(currencyType);
		countryJpaRepository.save(country);
		
	}



	
	

}
