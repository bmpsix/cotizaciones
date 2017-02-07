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
import com.unimer.cotizaciones.entities.StudyCategory;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.CountryJpaRepository;
import com.unimer.cotizaciones.repositories.LogCountryJpaRepository;

import com.unimer.cotizaciones.services.CountryService;;

@Service("countryServiceImpl")
public class CountryServiceImpl  implements CountryService{

	@Autowired
	@Qualifier("countryJpaRepository")
	private CountryJpaRepository countryJpaRepository; 
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logCountryJpaRepository")
	private LogCountryJpaRepository logCountryJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(CountryServiceImpl.class);
	
	@Override
	public void addCountry(Country country) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Country");

		if (consecutive != null) {
			country.setIdCountry(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!country.getIdCountry().equals(countryJpaRepository.findOne(country.getIdCountry()))
					&& countryJpaRepository.findByDetail(country.getDetail())  == null
					&& countryJpaRepository.findByCod(country.getCod()) == null) {

				countryJpaRepository.save(country);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else if (countryJpaRepository.findByIdCountry(country.getIdCountry()) != null) {
				Country countryToUpdate = countryJpaRepository.findByIdCountry(country.getIdCountry());
				java.util.Date date = new Date();
				LogCountry lcountry = new LogCountry(date, "Country  modified", "test", countryToUpdate.getDetail(),
				countryToUpdate.getIdCountry(), countryToUpdate.getCod());				
				
				countryJpaRepository.save(countryToUpdate);
				logCountryJpaRepository.save(lcountry);

			}
		} else {

			Consecutive ConsecutiveCountryDefault = new Consecutive();

			ConsecutiveCountryDefault.setType("Country");
			ConsecutiveCountryDefault.setPrefix("CTR");
			ConsecutiveCountryDefault.setSubfix(1);
			ConsecutiveCountryDefault.setDetail("Consecutivo por defecto para el manejo de los Países");
			consecutivesJpaRepository.save(ConsecutiveCountryDefault);

			country.setIdCountry(ConsecutiveCountryDefault.getPrefix() + "-" + ConsecutiveCountryDefault.getSubfix());

			if (!country.getIdCountry().equals(countryJpaRepository.findOne(country.getIdCountry()))
					&& countryJpaRepository.findByDetail(country.getDetail()) == null) {
				countryJpaRepository.save(country);
				ConsecutiveCountryDefault.setSubfix(ConsecutiveCountryDefault.getSubfix() + 1);
				consecutivesJpaRepository.save(ConsecutiveCountryDefault);
			} else {
				LOG.info("METHOD: addCountry in CountryServiceImpl -- The country id already exists ");
				return;
			}
		}
		
	}
	
	
	@Override
	public List<Country> listAllCountries() {
		List<Country> country = countryJpaRepository.findAll();
		return country;
	}

	@Override
	public Country findById(String idCountry) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
