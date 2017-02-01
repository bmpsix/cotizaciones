package com.unimer.cotizaciones.services.impl;


import java.util.List;

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
	
	@Override
	public Consecutive addConsecutive(Consecutive consecutive) {
		Consecutive obj_consecutive = consecutivesJpaRepository.save(consecutive);
		return  obj_consecutive;
	}

	@Override
	public List<Consecutive> listAllConsecutives() {
		List<Consecutive> consecutives = consecutivesJpaRepository.findAll();
		return consecutives;
	}

	@Override
	public Consecutive findConsecutiveById(int id) {
		
		return consecutivesJpaRepository.findOne(id);
		
	}

	@Override
	public void removeConsecutive(int id) {
		Consecutive consecutive = findConsecutiveById(id);
		if(consecutive!=null) consecutivesJpaRepository.delete(consecutive);

	}

}
