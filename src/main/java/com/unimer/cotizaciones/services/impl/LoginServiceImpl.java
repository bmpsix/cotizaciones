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

import com.unimer.cotizaciones.entities.UserRole;
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
		
		List<GrantedAuthority> authorities = buildAuthorities(user.getRol());
		return buildUser(user, authorities);
		
	}

	private User buildUser(com.unimer.cotizaciones.entities.User user, List<GrantedAuthority> authorities) {
		return new User(user.getEmail(), user.getPassword(),user.getStatus(),true,true,true, authorities);
	}

	private List<GrantedAuthority> buildAuthorities(Set<UserRole> set) {
		
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

		for (UserRole userRole : set) {
			LOG.info("EL ROLES ES:"+userRole.getRole());
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		return new ArrayList<GrantedAuthority>(auths);
	}
}

