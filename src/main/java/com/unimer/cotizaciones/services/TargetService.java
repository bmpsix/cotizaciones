package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.Target;

public interface TargetService {

public abstract Consecutive getConsecutive();
	
	public abstract Target addTarget(Target target);
	
	public abstract List<Target> listAllTargets();
	
	public abstract Target findById(String idTarget);
	public abstract void IP(String string);
	
}
