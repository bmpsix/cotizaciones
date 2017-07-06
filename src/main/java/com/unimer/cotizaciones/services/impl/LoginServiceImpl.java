package com.unimer.cotizaciones.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.Rol;
import com.unimer.cotizaciones.services.UserService;

@Service("authService")
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userServiceImpl;
	
	
	private static final Log LOG = LogFactory.getLog(LoginServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		com.unimer.cotizaciones.entities.User user = userServiceImpl.findByEmail(email);
		if(user!=null)
		{
			Set<Rol> roles = new HashSet<Rol>();
			roles.add(user.getRol());
			List<GrantedAuthority> authorities = buildAuthorities(roles);
			return buildUser(user, authorities);
		}
		else return  null;
		
		
	}

	private User buildUser(com.unimer.cotizaciones.entities.User user, List<GrantedAuthority> authorities) {
		boolean status=false;
		if(user.getStatus()==1) status=true;
		return new User(user.getEmail(), user.getPassword(),status,converter(user.getExpired()),converter(user.getCredentialExpired()),true, authorities);
	}

	private List<GrantedAuthority> buildAuthorities(Set<Rol> roles) {
		
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

		for (Rol rol : roles) {
			LOG.info("EL ROLES ES:"+rol.getDetail());
			auths.add(new SimpleGrantedAuthority("ROLE_"+rol.getDetail().toUpperCase()));
		}
		
		LOG.info("EL GrantedAuthority ES:"+auths.toString());

		return new ArrayList<GrantedAuthority>(auths);
	}
	
	
	private boolean converter(byte value)
	{
		if(value==0) return true;
		else return false;
	}
	
	
}

