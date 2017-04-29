package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.LogCountry;
import com.unimer.cotizaciones.repositories.CountryJpaRepository;
import com.unimer.cotizaciones.repositories.LogCountryJpaRepository;
import com.unimer.cotizaciones.services.CountryService;

@Service("countryServiceImpl")
public class CountryServiceImpl implements CountryService {


	@Autowired
	@Qualifier("countryJpaRepository")
	private CountryJpaRepository countryJpaRepository;

	@Autowired
	@Qualifier("logCountryJpaRepository")
	private LogCountryJpaRepository logCountryJpaRepository;
	

	private static final Log LOG = LogFactory.getLog(CountryServiceImpl.class);
	
	String ipCliente="";
	
	
	@Override
	public void addCountry(Country country) {

			if (country.getIdCountry()==0) {
				
				countryJpaRepository.save(country);
				LOG.info("METHOD: addCountry in CountryServiceImpl -- PARAMS: " + country.toString());
				
			} else {
				updateCountry(country);
			}

		} 

	
	@Override
	public List<Country> listAllCountries() {
		return countryJpaRepository.findAll();
	}	

	@Override
	public Country findById(int idCountry) {
		return countryJpaRepository.findByIdCountry(idCountry);
	}

	
	private void updateCountry(Country country) {
		
		java.util.Date date = new Date();
		Country countryToUpdate = countryJpaRepository.findByIdCountry(country.getIdCountry());
		if (countryToUpdate != null) {
			LogCountry logCountry = new LogCountry(date, "Country  modified", "test", countryToUpdate.getIdCountry(), countryToUpdate.getDetail(),
					countryToUpdate.getCod());
			LOG.info("METHOD: addCountry in CountryServiceImpl -- PARAMS: " + logCountry.toString());
			countryJpaRepository.save(country);
			logCountryJpaRepository.save(logCountry);
			
			
		}
	}
	

}
