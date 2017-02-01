package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.services.ConsecutiveService;

@Service("consecutiveServiceImpl")
public class ConsecutiveServiceImpl implements ConsecutiveService {

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	private static final Log LOG = LogFactory.getLog(ConsecutiveServiceImpl.class);
	
	@Override
	public Consecutive addConsecutive(Consecutive consecutive) {
		Consecutive obj_consecutive = findConsecutiveByType(consecutive.getType());
		if (obj_consecutive == null) {
			LOG.info("METHOD: igualnull conse -- PARAMS: " + consecutive.toString());
			consecutivesJpaRepository.save(consecutive);
			return consecutive;
		} else if (obj_consecutive.getPrefix() == consecutive.getPrefix()
				&& (obj_consecutive.getSubfix() <= consecutive.getSubfix())) {
			consecutivesJpaRepository.save(consecutive);
			LOG.info("METHOD: igualnull conse -- PARAMS: " + consecutive.toString());
			LOG.info("METHOD: igualnull obj -- PARAMS: " + obj_consecutive.toString());
			return consecutive;
		} else
			return obj_consecutive;

	}

	@Override
	public List<Consecutive> listAllConsecutives() {
		List<Consecutive> consecutives = consecutivesJpaRepository.findAll();
		return consecutives;
	}

	@Override
	public Consecutive findConsecutiveByType(String type) {

		return consecutivesJpaRepository.findOne(type);

	}

	@Override
	public void removeConsecutive(String type) {
		Consecutive consecutive = findConsecutiveByType(type);
		if (consecutive != null)
			consecutivesJpaRepository.delete(consecutive);

	}

}
