package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.LogCurrencyExchange;
import com.unimer.cotizaciones.repositories.CurrencyExchangeJpaRepository;
import com.unimer.cotizaciones.repositories.LogCurrencyExchangeJpaRepository;
import com.unimer.cotizaciones.services.CurrencyExchangeService;

@Service("currencyExchangeServiceImpl")
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
	
	@Autowired
	@Qualifier("currencyExchangeJpaRepository")
	private CurrencyExchangeJpaRepository currencyExchangeJpaRepository;
	
	@Autowired
	@Qualifier("logCurrencyExchangeJpaRepository")
	private LogCurrencyExchangeJpaRepository logCurrencyExchangeJpaRepository;
	

	
	private static final Log LOG = LogFactory.getLog(CurrencyExchangeServiceImpl.class);
	
	String ipCliente="";

	@Override
	public void addCurrencyExchange(CurrencyExchange currencyExchange) {
		
		if (currencyExchange.getIdCurrencyExchange()==0) {
				
				currencyExchangeJpaRepository.save(currencyExchange);
				LOG.info("METHOD: addCurrencyExchange in currencyExchangeJpaRepository -- PARAMS: " + currencyExchange.toString());
				
			} else {
				updateCurrencyExchange(currencyExchange);
			}

		} 

	@Override
	public List<CurrencyExchange> listAllCurrencyExchange() {
		return currencyExchangeJpaRepository.findAll();
	}

	@Override
	public boolean removeCurrencyExchange(int idCurrencyExchange) {
		if(currencyExchangeJpaRepository.existsById(idCurrencyExchange)){
			return false;
		}else{
			currencyExchangeJpaRepository.deleteById(idCurrencyExchange);
			return true;
		}
	}

	private void updateCurrencyExchange(CurrencyExchange currencyExchange) {
		
		java.util.Date date = new Date();
		currencyExchange.setDate(new Date());
		CurrencyExchange currencyExchangeToUpdate = currencyExchangeJpaRepository.findByIdCurrencyExchange(currencyExchange.getIdCurrencyExchange());
		LOG.info("METHOD: currencyExchangeToUpdate in currencyExchangeJpaRepository -- PARAMS: currencyExchangeToUpdate" + currencyExchange.toString() + currencyExchangeToUpdate.toString() );
		if (currencyExchangeToUpdate != null) {
			LogCurrencyExchange logCurrencyExchange = new LogCurrencyExchange(date, "Currency Exchange  modified", "test", currencyExchange.getBuy(),
					currencyExchange.getDate(),currencyExchange.getCountry().getIdCountry(),currencyExchange.getIdCurrencyExchange(),currencyExchange.getCurrencyType().getIdCurrencyType(),currencyExchange.getSell());
			currencyExchangeJpaRepository.save(currencyExchange);
			logCurrencyExchangeJpaRepository.save(logCurrencyExchange);
		
		}
		
	}

	@Override
	public CurrencyExchange getCurrencyExchange(int idCurrencyExchange) {
		return currencyExchangeJpaRepository.findByIdCurrencyExchange(idCurrencyExchange);
	}
}	