package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Rol;



public interface RolService {

	public abstract void addRol(Rol rol, int idUser);
	
	public abstract List<Rol> listAllRol();
	
	public abstract List<Rol> findByActiveStatus();
	
	public abstract void updateStatusById(int idRol, byte status);
	
	public abstract Rol findById(int idRol);
	
}
