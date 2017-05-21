package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.CollectMethod;

public interface CollectMethodService {
	
	
	public abstract void addCollectMethod(CollectMethod collectmethod, int idUser);
	
	public abstract List<CollectMethod> listAllCollectMethod();
	
	public abstract boolean removeCollectMethod(int idCollectMethod);
	
	public abstract CollectMethod getCollectMethod(int idCollectMethod);
	
	
}
