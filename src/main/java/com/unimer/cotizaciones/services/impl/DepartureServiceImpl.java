package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogDeparture;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Departure;
import com.unimer.cotizaciones.repositories.LogDepartureJpaRepository;
import com.unimer.cotizaciones.repositories.DepartureJpaRepository;
import com.unimer.cotizaciones.services.DepartureService;


@Service("departureServiceImpl")
public class DepartureServiceImpl implements DepartureService{

	@Autowired
	@Qualifier("departureJpaRepository")
	private DepartureJpaRepository departureJpaRepository;

	@Autowired
	@Qualifier("logDepartureJpaRepository")
	private LogDepartureJpaRepository logDepartureJpaRepository;	
	
	private static final Log LOG = LogFactory.getLog(DepartureServiceImpl.class);
	
	@Override
	public void addDeparture(Departure departure, int idUser) {
	

			if (departure.getIdDeparture()==0) {
				
				departureJpaRepository.save(departure);
				LOG.info("METHOD: addDeparture in DepartureServiceImpl -- PARAMS: " + departure.toString());
			
			} else {
				 updateDeparture(departure, idUser);
			}

		} 
	
	@Override
	public List<Departure> listAllDeparture() {
		return departureJpaRepository.findAll();
	}

	@Override
	public Departure findById(int idDeparture) {
		return departureJpaRepository.findByIdDeparture(idDeparture);
	}
	
	private void updateDeparture(Departure departure, int idUser) {
		java.util.Date date = new Date();
		Departure departureToUpdate = departureJpaRepository.findByIdDeparture(departure.getIdDeparture());
		if (departureToUpdate != null) {
			LogDeparture logDeparture = new LogDeparture(date, "Departure modified",idUser, departureToUpdate.getDetail(),departureToUpdate.getIdDeparture(),departureToUpdate.getStatus(),departureToUpdate.getCurrencyType().getIdCurrencyType(),departureToUpdate.getPrice());
			departureJpaRepository.save(departure);
			logDepartureJpaRepository.save(logDeparture);
		
		}
	}
	
	@Override
	public Departure findByDetail(String detail) {
		return departureJpaRepository.findByDetail(detail);
	}

	@Override
	public List<Departure> findDepartureByCountry(Country country) {
		return departureJpaRepository.findDepartureByCountry(country);
	}
	
}
