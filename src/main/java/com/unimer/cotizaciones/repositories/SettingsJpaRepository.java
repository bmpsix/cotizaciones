package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Settings;

@Repository("settingsJpaRepository")
public interface SettingsJpaRepository extends JpaRepository<Settings, Serializable>{

	public abstract Settings findSettingsByCountry(Country country);
	
	public abstract Settings findByIdSettings(int idSettings);
}
