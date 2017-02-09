package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.CollectMethod;
import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogCollectMethod;
import com.unimer.cotizaciones.repositories.CollectMethodJpaRepository;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogCollectMethodJpaRepository;
import com.unimer.cotizaciones.services.CollectMethodService;

@Service("collectMethodServiceImpl")
public class CollectMethodServiceImpl implements CollectMethodService{
	
	@Autowired
	@Qualifier("collectMethodJpaRepository")
	private CollectMethodJpaRepository collectMethodJpaRepository;
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logCollectMethodJpaRepository")
	private LogCollectMethodJpaRepository logCollectMethodJpaRepository;

	
	private static final Log LOG = LogFactory.getLog(CollectMethodServiceImpl.class);

	@Override
	public CollectMethod addCollectMethod(CollectMethod collectmethod) {
		
		Consecutive consecutive = consecutivesJpaRepository.findByType("Collect Method");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Collect Method");
			consecutive.setPrefix("COM");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Collect Method");
			consecutivesJpaRepository.save(consecutive);
			collectmethod.setIdCollectMethod(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!collectmethod.getIdCollectMethod().equals(collectMethodJpaRepository.findOne(collectmethod.getIdCollectMethod()))) {
				
				collectMethodJpaRepository.save(collectmethod);
				LOG.info("METHOD: addCollectMethod in CollectMethodServiceImpl -- PARAMS: " + collectmethod.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				updateCollectMethod(collectmethod);
			}

		} else if (collectmethod.getIdCollectMethod() == null) {

			collectmethod.setIdCollectMethod(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!collectmethod.getIdCollectMethod().equals(collectMethodJpaRepository.findOne(collectmethod.getIdCollectMethod()))) {
				LOG.info("METHOD: addCollectMethod in CollectMethodServiceImpl -- PARAMS: " + collectmethod.toString());
				collectMethodJpaRepository.save(collectmethod);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateCollectMethod(collectmethod);
			}
		} else {
			updateCollectMethod(collectmethod);
		}
		
		return collectmethod;
		
	}

	@Override
	public void updateCollectMethod(CollectMethod collectMethod) {
		
		java.util.Date date = new Date();
		CollectMethod collectMethodToUpdate = collectMethodJpaRepository.findOne(collectMethod.getIdCollectMethod());
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
	public boolean removeCollectMethod(String id) {
		if(collectMethodJpaRepository.exists(id)){
			return false;
		}else{
			collectMethodJpaRepository.delete(id);
			return true;
		}
		
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Collect Method");
	}

	@Override
	public CollectMethod getCollectMethod(String idCollectMethod) {
		return collectMethodJpaRepository.findOne(idCollectMethod);
	}
	
	

	
}
