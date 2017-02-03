package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.CollectMethod;



public interface CollectMethodService {
	
	public abstract CollectMethod addCollectMethod(CollectMethod collectmethod);
	
	public abstract List<CollectMethod> listAllCollectMethod();
	
	public abstract boolean removeCollectMethod(String id);

}
