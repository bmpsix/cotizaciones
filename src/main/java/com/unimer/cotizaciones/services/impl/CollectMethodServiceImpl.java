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
import com.unimer.cotizaciones.entities.LogRol;
import com.unimer.cotizaciones.entities.Rol;
import com.unimer.cotizaciones.repositories.CollectMethodJpaRepository;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogCollectMethodJpaRepository;
import com.unimer.cotizaciones.repositories.LogRolJpaRepository;
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

	private Consecutive consecutive;
	
	//private static final Log LOG = LogFactory.getLog(CollectMethodServiceImpl.class);;

	@Override
	public CollectMethod addCollectMethod(CollectMethod collectmethod) {
		
		consecutive = consecutivesJpaRepository.findByType("Collect Method");
		
		if(consecutive == null){
			
			consecutive = new Consecutive();

			consecutive.setType("Collect Method");
			consecutive.setPrefix("COM");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default Consecutive of Collect Method");
			
			consecutivesJpaRepository.save(consecutive);

			collectmethod.setIdCollectMethod(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!collectmethod.getIdCollectMethod().equals(collectMethodJpaRepository.findOne(collectmethod.getIdCollectMethod()))) {
				
				collectMethodJpaRepository.save(collectmethod);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				
			} else {
				
				collectmethod = null;
			}
			
		}else{
			
			collectmethod.setIdCollectMethod(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!collectmethod.getIdCollectMethod().equals(collectMethodJpaRepository.findOne(collectmethod.getIdCollectMethod()))) {

				collectMethodJpaRepository.save(collectmethod);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				
			} else {
				
				collectmethod = null;
			}
			
		}
		
		return collectmethod;
		
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
	
}
