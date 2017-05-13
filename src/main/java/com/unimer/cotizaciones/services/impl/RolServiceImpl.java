package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogRol;
import com.unimer.cotizaciones.entities.Rol;
import com.unimer.cotizaciones.repositories.LogRolJpaRepository;
import com.unimer.cotizaciones.repositories.RolJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.RolService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("rolServiceImpl")
public class RolServiceImpl implements RolService {


	@Autowired
	@Qualifier("rolJpaRepository")
	private RolJpaRepository rolJpaRepository;

	@Autowired
	@Qualifier("logRolJpaRepository")
	private LogRolJpaRepository logRolJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	String ipCliente="";

	private static final Log LOG = LogFactory.getLog(RolServiceImpl.class);
	
	
	@Override
	public void addRol(Rol rol) {

		if (rol.getIdRol()==0) {
				
				rolJpaRepository.save(rol);
				LOG.info("METHOD: addRol in RolServiceImpl -- PARAMS: " + rol.toString());

			} else {
				updateRol(rol);
			}

	} 
	

	@Override
	public List<Rol> listAllRol() {
		return rolJpaRepository.findAll();
	}

	@Override
	public void updateStatusById(int idRol, byte status) {

		Rol rol = rolJpaRepository.findByIdRol(idRol);

		if (rol != null) {
			rol.setStatus(status);
			rolJpaRepository.save(rol);
		}
	}

	@Override
	public Rol findById(int idRol) {
		return rolJpaRepository.findByIdRol(idRol);
	}

	@Override
	public List<Rol> findByActiveStatus() {
		return rolJpaRepository.findByStatus((byte) 1);
	}
	
	private void updateRol(Rol rol) {
		java.util.Date date = new Date();
		Rol rolToUpdate = rolJpaRepository.findByIdRol(rol.getIdRol());
		if (rolToUpdate != null) {
			LogRol logRol = new LogRol(date, "Role  modified", "test", rolToUpdate.getDetail(), rolToUpdate.getIdRol(),
					rolToUpdate.getStatus());
			rolJpaRepository.save(rol);
			logRolJpaRepository.save(logRol);
		}
	}
	
	
}
