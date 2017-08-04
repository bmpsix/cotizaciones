package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.ClientContact;
import com.unimer.cotizaciones.entities.Country;


@Repository("clientContactJpaRepository")
public interface ClientContactJpaRepository extends JpaRepository<ClientContact, Serializable>{
	
	public abstract List<ClientContact> findByStatus(byte status);
	
	public abstract ClientContact findByIdClientContact(int idClientContact);
	
	public abstract List<ClientContact> findByCountry(Country country);
	
	public abstract List<ClientContact> findByCountryAndStatus(Country country, byte status);
}