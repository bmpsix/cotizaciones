package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.CollectMethod;
import com.unimer.cotizaciones.entities.Consecutive;



public interface CollectMethodService {
	
	public abstract Consecutive getConsecutive();
	
	public abstract CollectMethod addCollectMethod(CollectMethod collectmethod);
	
	public abstract List<CollectMethod> listAllCollectMethod();
	
	public abstract boolean removeCollectMethod(String idCollectMethod);
	
	public abstract void updateCollectMethod(CollectMethod collectMethod);
	
	public abstract CollectMethod getCollectMethod(String idCollectMethod);
	
}
