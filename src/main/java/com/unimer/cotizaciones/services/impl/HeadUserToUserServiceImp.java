package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.controllers.ClientController;
import com.unimer.cotizaciones.entities.HeadUserToUser;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.HeadUserToUserJpaRepository;
import com.unimer.cotizaciones.repositories.UserJpaRepository;
import com.unimer.cotizaciones.services.HeadUserToUserService;


@Service("headUserToUserServiceImpl")
public class HeadUserToUserServiceImp implements HeadUserToUserService {

	@Autowired
	@Qualifier("headUserToUserJpaRepository")
	private HeadUserToUserJpaRepository headUserToUserJpaRepository;
	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(ClientController.class);
	
	@Override
	public List<HeadUserToUser> findUserByHeadUser(User headUser) {
		return headUserToUserJpaRepository.findUserByHeadUser(headUser);
	}

	@Override
	public HeadUserToUser findByUser(User user) {
		return headUserToUserJpaRepository.findHeadUserToUserByUser(user);
	}

	@Override
	public void addHeadUserToUser(int idHeadUser, User user) {
		LOG.info("idHeadUSer "+idHeadUser+" user: "+user.toString());
		if(idHeadUser!=0)
		{
			User headUser = userJpaRepository.findByIdUser(idHeadUser);
			HeadUserToUser headUserToUserOld = headUserToUserJpaRepository.findHeadUserToUserByUser(user);
			if(headUserToUserOld!=null) headUserToUserJpaRepository.delete(headUserToUserOld);
			HeadUserToUser headUserToUser = new HeadUserToUser(headUser,user);
			headUserToUserJpaRepository.save(headUserToUser);
		}
		else
		{
			HeadUserToUser headUserToUserOld = headUserToUserJpaRepository.findHeadUserToUserByUser(user);
			LOG.info("");
			if(headUserToUserOld!=null)headUserToUserJpaRepository.delete(headUserToUserOld);
			
		}
		
		
	}

	@Override
	public List<HeadUserToUser> findHeadUserToUser() {
		return headUserToUserJpaRepository.findAll();
	}

	@Override
	public void delete(HeadUserToUser headUserToUser) {
		headUserToUserJpaRepository.delete(headUserToUser);
		
	}


}
