package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Settings;

public interface SettingsService {

	public abstract void addSettings(Settings settings, int idUser);
	
	public abstract Settings findSettingByCountry(Country country);
	
	public abstract List<Settings> listAllSaClient();
	
	public abstract Settings findById(int idSettings);
	
}
