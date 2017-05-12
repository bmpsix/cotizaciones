package com.unimer.cotizaciones.services.impl;

//import java.util.Date;
import java.util.List;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.UserRole;
import com.unimer.cotizaciones.repositories.LogRolJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.repositories.UserRoleJpaRepository;
import com.unimer.cotizaciones.services.UserRoleService;

@Service("userRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService {


	@Autowired
	@Qualifier("userRoleJpaRepository")
	private UserRoleJpaRepository userRoleJpaRepository;

	@Autowired
	@Qualifier("logRolJpaRepository")
	private LogRolJpaRepository logRolJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	

	//private static final Log LOG = LogFactory.getLog(UserRoleServiceImpl.class);


	@Override
	public UserRole addRol(UserRole userRole) {
		return userRoleJpaRepository.save(userRole);
	}


	@Override
	public List<UserRole> listAllRol() {
		return userRoleJpaRepository.findAll();
	}


	@Override
	public UserRole findByUserRoleId(int idRole) {
		return userRoleJpaRepository.findByUserRoleId(idRole);
	}


	@Override
	public UserRole findByRole(String role) {
		return userRoleJpaRepository.findByRole(role);
	}
	
	
	
	
	
}
