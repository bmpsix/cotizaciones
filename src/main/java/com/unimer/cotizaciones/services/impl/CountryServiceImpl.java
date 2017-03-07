package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.LogCountry;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.CountryJpaRepository;
import com.unimer.cotizaciones.repositories.LogCountryJpaRepository;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("countryServiceImpl")
public class CountryServiceImpl implements CountryService {


	@Autowired
	@Qualifier("countryJpaRepository")
	private CountryJpaRepository countryJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logCountryJpaRepository")
	private LogCountryJpaRepository logCountryJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;

	private static final Log LOG = LogFactory.getLog(CountryServiceImpl.class);
	
	String ipCliente="";
	
	
	@Override
	public Country addCountry(Country country) {

		Consecutive consecutive = consecutivesJpaRepository.findByType("Country");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Country");
			consecutive.setPrefix("CTR");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of country table");
			consecutivesJpaRepository.save(consecutive);
			country.setIdCountry(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!country.getIdCountry().equals(countryJpaRepository.findOne(country.getIdCountry()))) {
				
				countryJpaRepository.save(country);
				LOG.info("METHOD: addCountry in CountryServiceImpl -- PARAMS: " + country.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se actualizo un nuevo sevicio de pais");
			} else {
				updateCountry(country);
			}

		} else if (country.getIdCountry() == null) {

			country.setIdCountry(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!country.getIdCountry().equals(countryJpaRepository.findOne(country.getIdCountry()))) {
				LOG.info("METHOD: addCountry in CountryServiceImpl -- PARAMS: " + country.toString());
				countryJpaRepository.save(country);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agrego un servicio de pais");
			} else {
				updateCountry(country);
			}
		} else {
			updateCountry(country);
		}
		return country;
	}

	
	@Override
	public List<Country> listAllCountries() {
		return countryJpaRepository.findAll();
	}	

	@Override
	public Country findById(String idCountry) {
		return countryJpaRepository.findByIdCountry(idCountry);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Country");
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
			
			insertBinnacle("Se actualizo un servicio de pais");
		}
	}
	
	@Override
	public void IP(String ip) {
		ipCliente=ip;
		
	}
	
	private void insertBinnacle(String msg)
	{
		TraceResponse traceResponse = new TraceResponse(null,"test",msg,ipCliente);
		traceResponseService.addTraceResponse(traceResponse);
	}
}
