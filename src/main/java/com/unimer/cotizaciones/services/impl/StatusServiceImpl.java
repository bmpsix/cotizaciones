package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogStatus;
import com.unimer.cotizaciones.entities.Status;
import com.unimer.cotizaciones.repositories.LogStatusJpaRepository;
import com.unimer.cotizaciones.repositories.StatusJpaRepository;
import com.unimer.cotizaciones.services.StatusService;

@Service("statusServiceImpl")
public class StatusServiceImpl implements StatusService {

	
	@Autowired
	@Qualifier("statusJpaRepository")
	private StatusJpaRepository statusJpaRepository;

	@Autowired
	@Qualifier("logStatusJpaRepository")
	private LogStatusJpaRepository logStatusJpaRepository;

	private static final Log LOG = LogFactory.getLog(StatusServiceImpl.class);
	
	
	@Override
	public void addStatus(Status status, int idUser) {
		
			if (status.getIdStatus()==0) {
				
				statusJpaRepository.save(status);
				LOG.info("METHOD: addStatus in StatuseServiceImpl -- PARAMS: " + status.toString());
			
			} else {
				updateStatus(status, idUser);
			}
	}

	@Override
	public List<Status> listAllStatus() {
		return statusJpaRepository.findAll();
	}

	@Override
	public Status findById(int idStatus) {
		return statusJpaRepository.findByIdStatus(idStatus);
	}


	private void updateStatus(Status status, int idUser) {
		java.util.Date date = new Date();
		Status statusToUpdate = statusJpaRepository.findByIdStatus(status.getIdStatus());
		if (statusToUpdate != null) {
			LogStatus logStatus = new LogStatus(date, "Status  modified", idUser, statusToUpdate.getDetail(), statusToUpdate.getIdStatus());
			statusJpaRepository.save(status);
			logStatusJpaRepository.save(logStatus);
			
		}
	}
}
