package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.unimer.cotizaciones.entities.QueryCatalog;
import com.unimer.cotizaciones.repositories.CollectMethodJpaRepository;
import com.unimer.cotizaciones.repositories.CountryJpaRepository;
import com.unimer.cotizaciones.repositories.IndustrySectorJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalTypeJpaRepository;
import com.unimer.cotizaciones.repositories.QueryCatalogJpaRepository;
import com.unimer.cotizaciones.repositories.StudyTypeJpaRepository;
import com.unimer.cotizaciones.services.QueryCatalogService;


@Service("queryCatalogServiceImpl")
public class QueryCatalogServiceImpl implements QueryCatalogService{

	@Autowired
	@Qualifier("queryCatalogJpaRepository")
	private QueryCatalogJpaRepository queryCatalogJpaRepository;
	
	@Autowired
	@Qualifier("countryJpaRepository")
	private CountryJpaRepository countryJpaRepository;
	
	@Autowired
	@Qualifier("collectMethodJpaRepository")
	private  CollectMethodJpaRepository collectMethodJpaRepository;
	
	@Autowired
	@Qualifier("industrySectorJpaRepository")
	private IndustrySectorJpaRepository industrySectorJpaRepository;
	
	@Autowired
	@Qualifier("studyTypeJpaRepository")
	private StudyTypeJpaRepository studyTypeJpaRepository;
	
	@Autowired
	@Qualifier("proposalTypeJpaRepository")
	private ProposalTypeJpaRepository proposalTypeJpaRepository;
	



	@Override
	public QueryCatalog getRowCount() {
		
		return null;
	}

	@Override
	public List<QueryCatalog> ListQueryCatalog() {
		
		return queryCatalogJpaRepository.findAll();
	}

	@Override
	public QueryCatalog addQuery(QueryCatalog queryCatalog) {
		if(!queryCatalog.getDetail().equals(queryCatalogJpaRepository.findByDetail(queryCatalog.getDetail())))
		{
			queryCatalogJpaRepository.save(queryCatalog);
		}
	else
		{
			updateQuery(queryCatalog);
		}
		return queryCatalog;					
	}

	@Override
	public void updateQuery(QueryCatalog queryCatalog) {
		
		QueryCatalog queryCatalogToUpdate = queryCatalogJpaRepository.findByDetail(queryCatalog.getDetail());
		if(queryCatalogToUpdate != null)
		{
			queryCatalogJpaRepository.save(queryCatalog);			
		}		
	}



	
	
	
	

}
