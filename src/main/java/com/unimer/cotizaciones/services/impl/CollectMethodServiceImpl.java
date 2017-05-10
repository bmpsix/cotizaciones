package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.CollectMethod;
import com.unimer.cotizaciones.entities.LogCollectMethod;
import com.unimer.cotizaciones.repositories.CollectMethodJpaRepository;
import com.unimer.cotizaciones.repositories.LogCollectMethodJpaRepository;
import com.unimer.cotizaciones.services.CollectMethodService;

@Service("collectMethodServiceImpl")
public class CollectMethodServiceImpl implements CollectMethodService{
	
	@Autowired
	@Qualifier("collectMethodJpaRepository")
	private CollectMethodJpaRepository collectMethodJpaRepository;
	
	@Autowired
	@Qualifier("logCollectMethodJpaRepository")
	private LogCollectMethodJpaRepository logCollectMethodJpaRepository;
	


	
	private static final Log LOG = LogFactory.getLog(CollectMethodServiceImpl.class);
	
	

	@Override
	public void addCollectMethod(CollectMethod collectmethod) {
		
	if (collectmethod.getIdCollectMethod()==0) {
				
				collectMethodJpaRepository.save(collectmethod);
				LOG.info("METHOD: addCollectMethod in CollectMethodServiceImpl -- PARAMS: " + collectmethod.toString());
				
			} else {
				updateCollectMethod(collectmethod);
			}

		} 

	@Override
	public void updateCollectMethod(CollectMethod collectMethod) {
		
		java.util.Date date = new Date();
		CollectMethod collectMethodToUpdate = collectMethodJpaRepository.findByIdCollectMethod(collectMethod.getIdCollectMethod());
		if (collectMethodToUpdate != null) {
			LogCollectMethod logCollectMethod = new LogCollectMethod(date, "Collect Method  modified", "test", collectMethodToUpdate.getDetail(), 
					collectMethodToUpdate.getIdCollectMethod());
			collectMethodJpaRepository.save(collectMethod);
			logCollectMethodJpaRepository.save(logCollectMethod);
			
		}
		
	}

	@Override
	public List<CollectMethod> listAllCollectMethod() {
		return collectMethodJpaRepository.findAll();
	}

	@Override
	public boolean removeCollectMethod(int id) {
		if(collectMethodJpaRepository.existsById(id)){
			return false;
		}else{
			collectMethodJpaRepository.deleteById(id);
			return true;
		}
		
	}

	@Override
	public CollectMethod getCollectMethod(int idCollectMethod) {
		return collectMethodJpaRepository.findByIdCollectMethod(idCollectMethod);
	}
	

	
}
