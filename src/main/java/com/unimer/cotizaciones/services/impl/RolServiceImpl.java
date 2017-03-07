package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogRol;
import com.unimer.cotizaciones.entities.Rol;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
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
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

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
	public Rol addRol(Rol rol) {

		Consecutive consecutive = consecutivesJpaRepository.findByType("Role");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Role");
			consecutive.setPrefix("ROL");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of role");
			consecutivesJpaRepository.save(consecutive);
			rol.setIdRol(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!rol.getIdRol().equals(rolJpaRepository.findOne(rol.getIdRol()))) {
				
				rolJpaRepository.save(rol);
				LOG.info("METHOD: addRol in RolServiceImpl -- PARAMS: " + rol.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó una rol");

			} else {
				updateRol(rol);
			}

		} else if (rol.getIdRol() == null) {

			rol.setIdRol(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!rol.getIdRol().equals(rolJpaRepository.findOne(rol.getIdRol()))) {
				LOG.info("METHOD: addRol in RolServiceImpl -- PARAMS: " + rol.toString());
				rolJpaRepository.save(rol);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó una rol");
			} else {
				updateRol(rol);
			}
		} else {
			updateRol(rol);
		}
		return rol;
	}

	@Override
	public List<Rol> listAllRol() {
		return rolJpaRepository.findAll();
	}

	@Override
	public void updateStatusById(String idRol, byte status) {

		Rol rol = rolJpaRepository.findOne(idRol);

		if (rol != null) {
			rol.setStatus(status);
			rolJpaRepository.save(rol);
		}
	}

	@Override
	public Rol findById(String idRol) {
		return rolJpaRepository.findByIdRol(idRol);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Role");
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
			insertBinnacle("Se actualizó un rol");
		}
	}
	
	
	@Override
	public void IP(String ip) {
		ipCliente=ip;
		
	}
	
	private void insertBinnacle(String msg)
	{
		TraceResponse traceResponse = new TraceResponse(null,"test",msg,ipCliente);
		traceResponseService.addTraceResponse(traceResponse);
	}
	
}
