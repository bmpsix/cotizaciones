package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.LogSettings;
import com.unimer.cotizaciones.entities.Settings;
import com.unimer.cotizaciones.repositories.LogSettingsJpaRepository;
import com.unimer.cotizaciones.repositories.SettingsJpaRepository;
import com.unimer.cotizaciones.services.SettingsService;


@Service("settingsServiceImpl")
public class SettingsServiceImpl implements SettingsService {

	
	
	@Autowired
	@Qualifier("settingsJpaRepository")
	private SettingsJpaRepository settingsJpaRepository;

	@Autowired
	@Qualifier("logSettingsJpaRepository")
	private LogSettingsJpaRepository logSettingsJpaRepository;	

	
	private static final Log LOG = LogFactory.getLog(SettingsServiceImpl.class);
	
	@Override
	public void addSettings(Settings settings, int idUser) {

		if (settings.getIdSettings()==0) {
			
			settingsJpaRepository.save(settings);
			LOG.info("METHOD: addOperation in OperationServiceImpl -- PARAMS: " + settings.toString());
		
		} else {
			updateSettings(settings, idUser);
		}
	}

	@Override
	public Settings findSettingByCountry(Country country) {
		return settingsJpaRepository.findSettingsByCountry(country);
	}

	@Override
	public List<Settings> listAllSaClient() {
		return settingsJpaRepository.findAll();
	}

	@Override
	public Settings findById(int idSettings) {
		return settingsJpaRepository.findByIdSettings(idSettings);
	}
	
	private void updateSettings(Settings settings, int idUser) {
		java.util.Date date = new Date();
		Settings settingsToUpdate = settingsJpaRepository.findByIdSettings(settings.getIdSettings());
		if (settingsToUpdate != null) {
			LogSettings logSettings = new LogSettings(date, "Settings  modified", idUser,settingsToUpdate.getIdSettings(),settingsToUpdate.getAporteFijo(),settingsToUpdate.getFactor1(),settingsToUpdate.getFactor2(),settingsToUpdate.getImprevisto(),settingsToUpdate.getCountry().getIdCountry(),settingsToUpdate.getCurrencyType().getIdCurrencyType());
			settingsJpaRepository.save(settings);
			logSettingsJpaRepository.save(logSettings);
		
		}
	}	
}
