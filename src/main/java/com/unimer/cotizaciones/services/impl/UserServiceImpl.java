package com.unimer.cotizaciones.services.impl;


import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogUser;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogUserJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.repositories.UserJpaRepository;
import com.unimer.cotizaciones.services.TraceResponseService;
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
	
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	String ipCliente="";

	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

	//  protected static SecureRandom random = new SecureRandom();

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
				java.util.Date date = new Date();
				user.setLastLoggin(date);
				user.setExpiredAt(date);
				user.setCredentialExpiredAt(date);
				user.setCreationDate(date);
				user.setLastModification(date);
				user.setCommissionAmount(0);
				user.setUseCommission(0);
				user.setConfirmationToken(getToken(4));
				LOG.info("METHOD: addUser new1 in UserServiceImpl -- PARAMS: " + user.toString());
				userJpaRepository.save(user);
				LOG.info("METHOD: addUser new 2 in UserServiceImpl -- PARAMS: " + user.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó un usuario");

			} else {
				updateUser(user);
			}

		} else if (user.getIdUser() == null) {
			java.util.Date date = new Date();
			user.setIdUser(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			user.setLastLoggin(date);
			user.setExpiredAt(date);
			user.setCredentialExpiredAt(date);
			user.setCreationDate(date);
			user.setLastModification(date);
			user.setCommissionAmount(0);
			user.setUseCommission(0);
			user.setConfirmationToken(getToken(4));

			if (!user.getIdUser().equals(userJpaRepository.findOne(user.getIdUser()))) {
				LOG.info("METHOD: addUser in userServiceImpl -- PARAMS: " + user.toString());
				userJpaRepository.save(user);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agregó un usuario");
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
		user.setCommissionAmount(userToUpdate.getCommissionAmount());
		user.setConfirmationToken(userToUpdate.getConfirmationToken());
		user.setCreationDate(userToUpdate.getCreationDate());
		user.setCredentialExpiredAt(userToUpdate.getCredentialExpiredAt());
		user.setExpiredAt(date);
		user.setLastLoggin(userToUpdate.getLastLoggin());
		user.setLastModification(date);
		LOG.info("METHOD: updateUser in userServiceImpl -- PARAMS: " + user.toString());
		if (userToUpdate != null) {
			LogUser logUser = new LogUser(date, userToUpdate.getAccountBank(), "User modified", "test",
					userToUpdate.getCommissionAmount(), userToUpdate.getConfirmationToken(),
					userToUpdate.getCreationDate(), userToUpdate.getCredentialExpired(),
					userToUpdate.getCredentialExpiredAt(), userToUpdate.getExpired(), userToUpdate.getExpiredAt(),
					userToUpdate.getCountry().getIdCountry(), userToUpdate.getRol().getIdRol(),
					userToUpdate.getIdUser(), userToUpdate.getLastLoggin(), userToUpdate.getLastModification(),
					userToUpdate.getLastname(), userToUpdate.getMidname(), userToUpdate.getPassword(),
					userToUpdate.getStatus(), userToUpdate.getUseCommission(), userToUpdate.getUsername());
			userJpaRepository.save(user);
			logUserJpaRepository.save(logUser);
			insertBinnacle("Se actualizó un usuario");
		}
	}

	 private String getToken(int chars) {
	    String CharSet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
	    String Token = "";
	    for (int a = 1; a <= chars; a++) {
	        Token += CharSet.charAt(new Random().nextInt(CharSet.length()));
	    }
	    return Token;
	}
	
	/*public synchronized String generateToken() {
        long longToken = Math.abs( random.nextLong() );
        return  Long.toString( longToken, 16 );
        
	}*/
	
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
