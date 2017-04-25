package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.QueryCatalog;

public interface QueryCatalogService {
	
	public abstract QueryCatalog getRowCount();
	public abstract List<QueryCatalog> ListQueryCatalog();
	public abstract QueryCatalog addQuery(QueryCatalog queryCatalog);
	public abstract boolean  updateQuery(QueryCatalog queryCatalog);
	public abstract QueryCatalog  findQuery(String detail);
		
	
}
