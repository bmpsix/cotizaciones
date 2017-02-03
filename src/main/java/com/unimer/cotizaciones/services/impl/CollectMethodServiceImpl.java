package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.CollectMethod;
import com.unimer.cotizaciones.repositories.CollectMethodJpaRepository;
import com.unimer.cotizaciones.services.CollectMethodService;

@Service("collectMethodServiceImpl")
public class CollectMethodServiceImpl implements CollectMethodService{
	
	@Autowired
	@Qualifier("collectMethodJpaRepository")
	private CollectMethodJpaRepository collectMethodJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(CollectMethodServiceImpl.class);

	@Override
	public CollectMethod addCollectMethod(CollectMethod collectmethod) {
		LOG.info("METHOD: addConsecutiveService -- PARAMS: " + collectmethod.toString());
		return collectMethodJpaRepository.save(collectmethod);
	}

	@Override
	public List<CollectMethod> listAllCollectMethod() {
		return collectMethodJpaRepository.findAll();
	}

	@Override
	public boolean removeCollectMethod(String id) {
		if(collectMethodJpaRepository.exists(id)){
			return false;
		}else{
			collectMethodJpaRepository.delete(id);
			return true;
		}
		
	}
	
}
