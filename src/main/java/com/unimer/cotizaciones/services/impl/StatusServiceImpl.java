package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogStatus;
import com.unimer.cotizaciones.entities.Status;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogStatusJpaRepository;
import com.unimer.cotizaciones.repositories.StatusJpaRepository;
import com.unimer.cotizaciones.services.StatusService;

@Service("statusServiceImpl")
public class StatusServiceImpl implements StatusService {

	
	@Autowired
	@Qualifier("statusJpaRepository")
	private StatusJpaRepository statusJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logStatusJpaRepository")
	private LogStatusJpaRepository logStatusJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(StatusServiceImpl.class);
	
	
	@Override
	public Status addStatus(Status status) {
		
		Consecutive consecutive = consecutivesJpaRepository.findByType("Status");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Status");
			consecutive.setPrefix("STS");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for status entity");
			consecutivesJpaRepository.save(consecutive);
			status.setIdStatus(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!status.getIdStatus().equals(statusJpaRepository.findOne(status.getIdStatus()))) {
				
				statusJpaRepository.save(status);
				LOG.info("METHOD: addStatus in StatuseServiceImpl -- PARAMS: " + status.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				updateStatus(status);
			}

		} else if (status.getIdStatus() == null) {

			status.setIdStatus(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!status.getIdStatus().equals(statusJpaRepository.findOne(status.getIdStatus()))) {
				LOG.info("METHOD: addStatus in StatusServiceImpl -- PARAMS: " + status.toString());
				statusJpaRepository.save(status);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateStatus(status);
			}
		} else {
			updateStatus(status);
		}
		return status;
	}

	@Override
	public List<Status> listAllStatus() {
		return statusJpaRepository.findAll();
	}

	@Override
	public Status findById(String idStatus) {
		return statusJpaRepository.findByIdStatus(idStatus);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Status");
	}
	
	

	/*@Override
	public void updateStatusById(String idStatus, byte status) {

		Status statusEntity = statusJpaRepository.findOne(idStatus);

		if (statusEntity != null) {
			statusEntity.setStatus(status);
			statusJpaRepository.save(statusEntity);
		}
	}*/

	
	
	private void updateStatus(Status status) {
		java.util.Date date = new Date();
		Status statusToUpdate = statusJpaRepository.findByIdStatus(status.getIdStatus());
		if (statusToUpdate != null) {
			LogStatus logStatus = new LogStatus(date, "Status  modified", "test", statusToUpdate.getDetail(), statusToUpdate.getIdStatus());
			statusJpaRepository.save(status);
			logStatusJpaRepository.save(logStatus);
		}
	}


}
