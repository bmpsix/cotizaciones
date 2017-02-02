package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Rol;



public interface RolService {

	public abstract Rol addRol(Rol rol);
	
	public abstract List<Rol> listAllRol();
	
	public abstract Rol findRolById(String idRol );
	
	public abstract void changeStatusById(String idRol);
	
	
	
}
