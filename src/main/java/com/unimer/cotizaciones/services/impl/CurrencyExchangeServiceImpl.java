package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.LogCurrencyExchange;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.CurrencyExchangeJpaRepository;
import com.unimer.cotizaciones.repositories.LogCurrencyExchangeJpaRepository;
import com.unimer.cotizaciones.services.CurrencyExchangeService;

@Service("currencyExchangeServiceImpl")
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
	
	@Autowired
	@Qualifier("currencyExchangeJpaRepository")
	private CurrencyExchangeJpaRepository currencyExchangeJpaRepository;
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logCurrencyExchangeJpaRepository")
	private LogCurrencyExchangeJpaRepository logCurrencyExchangeJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(CurrencyExchangeServiceImpl.class);

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Currency exchange");
	}

	@Override
	public CurrencyExchange addCurrencyExchange(CurrencyExchange currencyExchange) {
		
		Consecutive consecutive = consecutivesJpaRepository.findByType("Currency exchange");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Currency exchange");
			consecutive.setPrefix("CUE");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Currency Exchange");
			consecutivesJpaRepository.save(consecutive);
			currencyExchange.setIdCurrencyExchange(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!currencyExchange.getIdCurrencyExchange().equals(currencyExchangeJpaRepository.findOne(currencyExchange.getIdCurrencyExchange()))) {
				
				currencyExchangeJpaRepository.save(currencyExchange);
				LOG.info("METHOD: addCurrencyExchange in currencyExchangeJpaRepository -- PARAMS: " + currencyExchange.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				updateCurrencyExchange(currencyExchange);
			}

		} else if (currencyExchange.getIdCurrencyExchange() == null) {

			currencyExchange.setIdCurrencyExchange(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!currencyExchange.getIdCurrencyExchange().equals(currencyExchangeJpaRepository.findOne(currencyExchange.getIdCurrencyExchange()))) {
				LOG.info("METHOD: addCurrencyExchange in currencyExchangeJpaRepository -- PARAMS: " + currencyExchange.toString());
				currencyExchangeJpaRepository.save(currencyExchange);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateCurrencyExchange(currencyExchange);
			}
		} else {
			updateCurrencyExchange(currencyExchange);
		}
		
		return currencyExchange;
	}

	@Override
	public List<CurrencyExchange> listAllCurrencyExchange() {
		return currencyExchangeJpaRepository.findAll();
	}

	@Override
	public boolean removeCurrencyExchange(String idCurrencyExchange) {
		if(currencyExchangeJpaRepository.exists(idCurrencyExchange)){
			return false;
		}else{
			currencyExchangeJpaRepository.delete(idCurrencyExchange);
			return true;
		}
	}

	private void updateCurrencyExchange(CurrencyExchange currencyExchange) {
		
		java.util.Date date = new Date();
		CurrencyExchange currencyExchangeToUpdate = currencyExchangeJpaRepository.findOne(currencyExchange.getIdCurrencyExchange());
		LOG.info("METHOD: currencyExchangeToUpdate in currencyExchangeJpaRepository -- PARAMS: currencyExchangeToUpdate" + currencyExchange.toString() + currencyExchangeToUpdate.toString() );
		if (currencyExchangeToUpdate != null) {
			LogCurrencyExchange logCurrencyExchange = new LogCurrencyExchange(date, "Currency Exchange  modified", "test", currencyExchange.getBuy(),
					currencyExchange.getDate(),currencyExchange.getCountry().getIdCountry(),currencyExchange.getIdCurrencyExchange(),currencyExchange.getCurrencyType().getIdCurrencyType(),currencyExchange.getSell());
			currencyExchangeJpaRepository.save(currencyExchange);
			logCurrencyExchangeJpaRepository.save(logCurrencyExchange);
		}
		
	}

	@Override
	public CurrencyExchange getCurrencyExchange(String idCurrencyExchange) {
		return currencyExchangeJpaRepository.findOne(idCurrencyExchange);
	}
	
	
}	