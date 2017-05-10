package com.unimer.cotizaciones.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.UserJpaRepository;

@Service("userService")
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.unimer.cotizaciones.entities.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}

	private User buildUser(com.unimer.cotizaciones.entities.User user, List<GrantedAuthority> authorities) {
		
		return new User(user.getUsername(),user.getPassword(),authorities);

	}

	private List<GrantedAuthority> buildAuthorities(Set<com.unimer.cotizaciones.entities.User> userRoles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

		for (com.unimer.cotizaciones.entities.User userRole : userRoles ) {
			auths.add(new SimpleGrantedAuthority(userRoles.));
		}

		return new ArrayList<GrantedAuthority>(auths);
	}


}
