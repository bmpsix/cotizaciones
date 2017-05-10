package com.unimer.cotizaciones.services.impl;


import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.LogUser;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.LogUserJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.repositories.UserJpaRepository;
import com.unimer.cotizaciones.services.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;

	
	@Autowired
	@Qualifier("logUserJpaRepository")
	private LogUserJpaRepository logUserJpaRepository;
	
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;

	
	String ipCliente="";

	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

	//  protected static SecureRandom random = new SecureRandom();

	@Override
	public void addUser(User user) {
		
			if (user.getIdUser()==0) {
				java.util.Date date = new Date();
				user.setLastLoggin(date);
				user.setExpiredAt(date);
				user.setCredentialExpiredAt(date);
				user.setCreationDate(date);
				user.setLastModification(date);
				user.setCommissionAmount(0);
				user.setUseCommission(0);
				user.setConfirmationToken(getToken(4));
				LOG.info("METHOD: addUser new in UserServiceImpl -- PARAMS: " + user.toString());
				userJpaRepository.save(user);
			

			} else {
				updateUser(user);
			}

	}

	@Override
	public List<User> listAllUser() {
		return userJpaRepository.findAll();
	}

	@Override
	public User findById(int idUser) {
		return userJpaRepository.findByIdUser(idUser);
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
			//revisa esto
			LogUser logUser = new LogUser(date, userToUpdate.getAccountBank(), "User modified", "test",
					userToUpdate.getCommissionAmount(), userToUpdate.getConfirmationToken(),
					userToUpdate.getCreationDate(), userToUpdate.getCredentialExpired(),
					userToUpdate.getCredentialExpiredAt(), userToUpdate.getExpired(), userToUpdate.getExpiredAt(),
					userToUpdate.getIdUser(), userToUpdate.getStatus(), userToUpdate.getLastLoggin(), userToUpdate.getLastModification(),
					userToUpdate.getLastname(), userToUpdate.getMidname(), userToUpdate.getPassword(),
					userToUpdate.getStatus(), userToUpdate.getUseCommission(), userToUpdate.getUsername());
			userJpaRepository.save(user);
			logUserJpaRepository.save(logUser);
			
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
	

}
