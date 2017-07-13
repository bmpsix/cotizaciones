package com.unimer.cotizaciones.services.impl;



import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.component.PasswordCreator;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.LogUser;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.LogUserJpaRepository;
import com.unimer.cotizaciones.repositories.UserJpaRepository;
import com.unimer.cotizaciones.services.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	@Qualifier("passwordCreator")
	private PasswordCreator passwordCreator;
	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;

	
	@Autowired
	@Qualifier("logUserJpaRepository")
	private LogUserJpaRepository logUserJpaRepository;
	

	/*@Autowired
	private JavaMailSender javaMailService;*/
	
	
	@Override
	public List<User> listAllUser() {
		return userJpaRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		int length = email.length();
		return userJpaRepository.findByEmail(email,length);
	}

	@Override
	public User findById(int idUser) {
		return userJpaRepository.findByIdUser(idUser);
	}

	
	@Override
	public void addUser(User user, int idUser) {
		
			User creationUser = userJpaRepository.findByIdUser(idUser);
			Country country = creationUser.getCountry();
			user.setCountry(country);
			/*String password = getToken(8);
			/user.setPassword(password);
			sendPassword(user.getEmail(),password);*/
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
				LOG.info("METHOD: ENCRYPT PASSWORD -- PARAMS: " + user.toString());
				user.setPassword(passwordCreator.EncodePass(user.getPassword()));
				LOG.info("METHOD: addUser new1 in UserServiceImpl -- PARAMS: " + user.toString());
				userJpaRepository.save(user);

			} else {
				updateUser(user,idUser);
			}

	}

	private void updateUser(User user, int idUser) {
		java.util.Date date = new Date();
		User userToUpdate = userJpaRepository.findByIdUser(user.getIdUser());
		user.setCommissionAmount(userToUpdate.getCommissionAmount());
		user.setConfirmationToken(userToUpdate.getConfirmationToken());
		user.setCreationDate(userToUpdate.getCreationDate());
		user.setCredentialExpiredAt(userToUpdate.getCredentialExpiredAt());
		user.setExpiredAt(date);
		user.setLastLoggin(userToUpdate.getLastLoggin());
		user.setLastModification(date);
		
		user.setPassword(passwordCreator.EncodePass(user.getPassword()));
		LOG.info("METHOD: updateUser  User in userServiceImpl -- PARAMS: " + user.toString());
		LOG.info("METHOD: updateUser  userToUpdate in userServiceImpl -- PARAMS: " + user.toString());
		if (userToUpdate != null) {
			LogUser logUser = new LogUser(date,userToUpdate.getAccountBank(),"User modified", idUser,userToUpdate.getCommissionAmount(),
					userToUpdate.getConfirmationToken(), userToUpdate.getCreationDate(), userToUpdate.getCredentialExpired(), userToUpdate.getCredentialExpiredAt(), userToUpdate.getExpired(),
					userToUpdate.getExpiredAt(), userToUpdate.getCountry().getIdCountry(), userToUpdate.getRol().getIdRol(), userToUpdate.getIdUser(), userToUpdate.getLastLoggin(),userToUpdate.getLastModification(),
					userToUpdate.getLastname(), userToUpdate.getMidname(), userToUpdate.getStatus(), userToUpdate.getUseCommission(), userToUpdate.getName(), userToUpdate.getEmail());
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

	@Override
	public void lastLogin(User user) {
		
		java.util.Date date = new Date();
		user.setLastLoggin(date);
		userJpaRepository.save(user);
	}

	@Override
	public List<User> findByCountry(Country country) {
		return userJpaRepository.findByCountry(country);
	}

	
	
	/*private void sendPassword(String email, String password)
	{
		
		try{
			MimeMessage mailMessage = javaMailService.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper( mailMessage, false, "utf-8" );
			helper.setTo(email);
			helper.setFrom("bot@unimercentroamerica.info");
			helper.setSubject("UNIMER clave de acceso");
			helper.setText("Clave: "+password);
			javaMailService.send( mailMessage );
			LOG.info("METHOD: sendPassword. Enviando a:"+email+"pass: "+password);
		}
		catch(Exception e)
		{
			LOG.info("METHOD: sendPassword. Error al enviar el correo "+e.toString());
		}
	}*/
	
	/*public synchronized String generateToken() {
        long longToken = Math.abs( random.nextLong() );
        return  Long.toString( longToken, 16 );
        
	}*/
	

}
	
	//  protected static SecureRandom random = new SecureRandom();


