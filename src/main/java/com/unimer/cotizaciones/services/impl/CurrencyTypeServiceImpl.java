package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.LogCurrencyType;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.CurrencyTypeJpaRepository;
import com.unimer.cotizaciones.repositories.LogCurrencyTypeJpaRepository;;

@Service("currencyTypeServiceImpl")
public class CurrencyTypeServiceImpl implements CurrencyTypeService{
	
	@Autowired
	@Qualifier("currencyTypeJpaRepository")
	private CurrencyTypeJpaRepository currencyTypeJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logCurrencyTypeJpaRepository")
	private LogCurrencyTypeJpaRepository logCurrencyTypeJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;

	private static final Log LOG = LogFactory.getLog(CurrencyTypeServiceImpl.class);
	
	String ipCliente="";

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Currency Type");
	}

	@Override
	public CurrencyType addCurrencyType(CurrencyType currencyType) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Currency Type");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Currency Type");
			consecutive.setPrefix("CUT");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Currency Type table");
			consecutivesJpaRepository.save(consecutive);
			currencyType.setIdCurrencyType(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!currencyType.getIdCurrencyType().equals(currencyTypeJpaRepository.findOne(currencyType.getIdCurrencyType()))) {
				
				currencyTypeJpaRepository.save(currencyType);
				LOG.info("METHOD: addCurrencyType in currencyTypeJpaRepository -- PARAMS: " + currencyType.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se actualizo un nuevo tipo de moneda");
			} else {
				updateCurrencyType(currencyType);
			}

		} else if (currencyType.getIdCurrencyType() == null) {

			currencyType.setIdCurrencyType(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!currencyType.getIdCurrencyType().equals(currencyTypeJpaRepository.findOne(currencyType.getIdCurrencyType()))) {
				LOG.info("METHOD: addCurrencyType in CountryServiceImpl -- PARAMS: " + currencyType.toString());
				currencyTypeJpaRepository.save(currencyType);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agrego un tipo de moneda");
			} else {
				updateCurrencyType(currencyType);
			}
		} else {
			updateCurrencyType(currencyType);
		}
		return currencyType;
	}

	@Override
	public List<CurrencyType> listAllCurrencyType() {
		return currencyTypeJpaRepository.findAll();
	}

	@Override
	public void updateCurrencyType(CurrencyType currencyType) {
		
		java.util.Date date = new Date();
		CurrencyType currencyTypeToUpdate = currencyTypeJpaRepository.findOne(currencyType.getIdCurrencyType());
		if (currencyTypeToUpdate != null) {
			LogCurrencyType logCurrencyType= new LogCurrencyType(date, "Currency Type  modified", "test", currencyTypeToUpdate.getDetail(), 
					currencyTypeToUpdate.getFavorite(),currencyTypeToUpdate.getIdCurrencyType(),currencyTypeToUpdate.getStatus(),currencyTypeToUpdate.getSymbol());
			currencyTypeJpaRepository.save(currencyType);
			logCurrencyTypeJpaRepository.save(logCurrencyType);
			
			insertBinnacle("Se actualizo un tipo de moneda");
		}
		
	}

	@Override
	public CurrencyType getCurrencyType(String idCurrencyType) {
		return currencyTypeJpaRepository.findOne(idCurrencyType);
	}
	
	@Override
	public void IP(String ip) {
		ipCliente=ip;
		
	}
	
	private void insertBinnacle(String msg)
	{
		Date date = new Date();
		TraceResponse traceResponse = new TraceResponse(null,"test",msg,ipCliente,date);
		traceResponseService.addTraceResponse(traceResponse);
	}
	

}
