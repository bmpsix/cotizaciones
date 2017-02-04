package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Rol;



public interface RolService {

	public abstract void addRol(Rol rol);
	
	public abstract List<Rol> listAllRol();
	
	public abstract void changeStatusById(String idRol, byte status);
	
	public abstract Rol findById(String idRol);
	
}
