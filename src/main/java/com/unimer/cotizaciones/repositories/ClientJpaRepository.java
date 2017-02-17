package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unimer.cotizaciones.entities.Client;

@Repository("clientJpaRepository")
public interface ClientJpaRepository extends JpaRepository<Client, Serializable> {

	public abstract Client findByDetail(String detail);

	public abstract Client  findByIdClient(String idClient);
	
	public abstract List<Client> findByStatus(byte status);

}