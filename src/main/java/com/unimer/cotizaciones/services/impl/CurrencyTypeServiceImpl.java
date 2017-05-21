package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.LogCurrencyType;
import com.unimer.cotizaciones.repositories.CurrencyTypeJpaRepository;
import com.unimer.cotizaciones.repositories.LogCurrencyTypeJpaRepository;;

@Service("currencyTypeServiceImpl")
public class CurrencyTypeServiceImpl implements CurrencyTypeService{
	
	@Autowired
	@Qualifier("currencyTypeJpaRepository")
	private CurrencyTypeJpaRepository currencyTypeJpaRepository;

	@Autowired
	@Qualifier("logCurrencyTypeJpaRepository")
	private LogCurrencyTypeJpaRepository logCurrencyTypeJpaRepository;

	private static final Log LOG = LogFactory.getLog(CurrencyTypeServiceImpl.class);

	@Override
	public void addCurrencyType(CurrencyType currencyType, int idUser) {
		
			if (currencyType.getIdCurrencyType()==0) {
				
				currencyTypeJpaRepository.save(currencyType);
				LOG.info("METHOD: addCurrencyType in currencyTypeJpaRepository -- PARAMS: " + currencyType.toString());
			
			} else {
				updateCurrencyType(currencyType, idUser);
			}

		}

	@Override
	public List<CurrencyType> listAllCurrencyType() {
		return currencyTypeJpaRepository.findAll();
	}

	
	private void updateCurrencyType(CurrencyType currencyType, int idUser) {
		
		java.util.Date date = new Date();
		CurrencyType currencyTypeToUpdate = currencyTypeJpaRepository.findByIdCurrencyType(currencyType.getIdCurrencyType());
		if (currencyTypeToUpdate != null) {
			LogCurrencyType logCurrencyType= new LogCurrencyType(date, "Currency Type  modified", idUser, currencyTypeToUpdate.getDetail(), 
					currencyTypeToUpdate.getFavorite(),currencyTypeToUpdate.getIdCurrencyType(),currencyTypeToUpdate.getStatus(),currencyTypeToUpdate.getSymbol());
			currencyTypeJpaRepository.save(currencyType);
			logCurrencyTypeJpaRepository.save(logCurrencyType);
			
		
		}
		
	}

	@Override
	public CurrencyType getCurrencyType(int idCurrencyType) {
		return currencyTypeJpaRepository.findByIdCurrencyType(idCurrencyType);
	}
	

}
