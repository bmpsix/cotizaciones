package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.Rol;



public interface RolService {

	public abstract Rol addRol(Rol rol);
	
	public abstract List<Rol> listAllRol();
	
	public abstract List<Rol> findByActiveStatus();
	
	public abstract void updateStatusById(String idRol, byte status);
	
	public abstract Rol findById(String idRol);
	
	public abstract Consecutive getConsecutive();
}
