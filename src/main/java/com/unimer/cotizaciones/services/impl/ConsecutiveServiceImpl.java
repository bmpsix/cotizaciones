package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogConsecutive;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogConsecutivesJpaRepository;
import com.unimer.cotizaciones.services.ConsecutiveService;

@Service("consecutiveServiceImpl")
public class ConsecutiveServiceImpl implements ConsecutiveService {

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logConsecutivesJpaRepository")
	private LogConsecutivesJpaRepository logConsecutivesJpaRepository;

	private static final Log LOG = LogFactory.getLog(ConsecutiveServiceImpl.class);

	@Override
	public Consecutive addConsecutive(Consecutive consecutive) {

		LOG.info("METHOD: addConsecutiveService -- PARAMS: " + consecutive.toString());
		if (consecutivesJpaRepository.findByType(consecutive.getType()) == null
				&& consecutivesJpaRepository.findByPrefix(consecutive.getPrefix()) == null
				&& logConsecutivesJpaRepository.findByPrefix(consecutive.getPrefix()) == null) {
			LOG.info("METHOD: addConsecutiveService 1r if -- PARAMS: " + consecutive.toString());
			consecutivesJpaRepository.save(consecutive);
			return consecutive;

		} else if (consecutivesJpaRepository.findByType(consecutive.getType()) != null
				&& consecutivesJpaRepository.findByPrefix(consecutive.getPrefix()) == null
				&& logConsecutivesJpaRepository.findByPrefix(consecutive.getPrefix()) == null) {

			LOG.info("METHOD: addConsecutiveService 2d if -- PARAMS: " + consecutive.toString());
			updateConsecutive(consecutive);

			return consecutive;

		} else if (consecutivesJpaRepository.findByType(consecutive.getType()) != null
				&& consecutivesJpaRepository.findByPrefix(consecutive.getPrefix()) != null
				&& logConsecutivesJpaRepository.findByPrefix(consecutive.getPrefix()) == null
				&& consecutivesJpaRepository.findByType(consecutive.getType()).getSubfix() <= consecutive.getSubfix()) {

			LOG.info("METHOD: addConsecutiveService 3r if -- PARAMS: " + consecutive.toString());
			updateConsecutive(consecutive);
			return consecutive;

		} else if (consecutivesJpaRepository.findByType(consecutive.getType()) != null
				&& consecutivesJpaRepository.findByPrefix(consecutive.getPrefix()) != null
				&& logConsecutivesJpaRepository.findByPrefix(consecutive.getPrefix()) != null
				&& consecutivesJpaRepository.findByType(consecutive.getType()).getSubfix() <= consecutive.getSubfix()) {

			LOG.info("METHOD: addConsecutiveService 4r if -- PARAMS: " + consecutive.toString());
			updateConsecutive(consecutive);
			return consecutive;

		} else {
			LOG.info("METHOD: addConsecutiveService else -- PARAMS: " + consecutive.toString());
			return consecutive;
		}

	}

	@Override
	public List<Consecutive> listAllConsecutives() {
		List<Consecutive> consecutives = consecutivesJpaRepository.findAll();
		return consecutives;
	}

	@Override
	public void removeConsecutive(String type) {
		Consecutive consecutive = consecutivesJpaRepository.findByType(type);
		if (consecutive != null)
			consecutivesJpaRepository.delete(consecutive);

	}

	private void updateConsecutive(Consecutive consecutive) {

		Consecutive consecutiveToUpdate = consecutivesJpaRepository.findByType(consecutive.getType());
		if (consecutiveToUpdate != null) {
			java.util.Date date = new Date();
			LogConsecutive logConsecutive = new LogConsecutive(date, "Consecutive  modified", "test",
					consecutiveToUpdate.getDetail(), consecutiveToUpdate.getPrefix(), consecutiveToUpdate.getSubfix(),
					consecutiveToUpdate.getType());
			consecutivesJpaRepository.save(consecutive);
			logConsecutivesJpaRepository.save(logConsecutive);
		}
	}

	@Override
	public Consecutive findByType(String type) {
		return consecutivesJpaRepository.findByType(type);
	}

}
