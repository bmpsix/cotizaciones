package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogUser;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogUserJpaRepository;
import com.unimer.cotizaciones.repositories.UserJpaRepository;
import com.unimer.cotizaciones.services.UserService;


@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logUserJpaRepository")
	private LogUserJpaRepository logUserJpaRepository;

	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);
	
	
	
	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("User");
	}

	@Override
	public User addUser(User user) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("User");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("User");
			consecutive.setPrefix("USR");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for user");
			consecutivesJpaRepository.save(consecutive);
			user.setIdUser(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!user.getIdUser().equals(userJpaRepository.findOne(user.getIdUser()))) {
				
				userJpaRepository.save(user);
				LOG.info("METHOD: addUser in UserServiceImpl -- PARAMS: " + user.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				 updateUser(user);
			}

		} else if (user.getIdUser() == null) {

			user.setIdUser(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!user.getIdUser().equals(userJpaRepository.findOne(user.getIdUser()))) {
				LOG.info("METHOD: addUser in userServiceImpl -- PARAMS: " + user.toString());
				userJpaRepository.save(user);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateUser(user);
			}
		} else {
			updateUser(user);
		}
		return user;
	}

	@Override
	public List<User> listAllUser() {
		return userJpaRepository.findAll();
	}

	@Override
	public User findById(String idUser) {
		return userJpaRepository.findOne(idUser);
	}
	
	
	private void updateUser(User user) {
		java.util.Date date = new Date();
		User userToUpdate = userJpaRepository.findByIdUser(user.getIdUser());
		if (userToUpdate != null) {
			LogUser logUser = new LogUser(date,userToUpdate.getAccountBank(),"User modified","test",userToUpdate.getCommissionAmount(),userToUpdate.getConfirmationToken(),userToUpdate.getCreationDate(),
					userToUpdate.getCredentialExpired(),userToUpdate.getCredentialExpiredAt(),userToUpdate.getExpired(),userToUpdate.getExpiredAt(),userToUpdate.getCountry().getIdCountry(),userToUpdate.getRol().getIdRol(),
					userToUpdate.getIdUser(),userToUpdate.getLastLoggin(),userToUpdate.getLastModification(),userToUpdate.getLastname(), userToUpdate.getMidname(),userToUpdate.getPassword(),
					userToUpdate.getStatus(),userToUpdate.getUseCommission(),userToUpdate.getUsername());
			userJpaRepository.save(user);
			logUserJpaRepository.save(logUser);
		}
	}

}
