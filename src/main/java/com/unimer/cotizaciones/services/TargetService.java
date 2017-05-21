package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Target;

public interface TargetService {

	
	public abstract void addTarget(Target target, int idUser);
	
	public abstract List<Target> listAllTargets();
	
	public abstract Target findById(int idTarget);
	
}
