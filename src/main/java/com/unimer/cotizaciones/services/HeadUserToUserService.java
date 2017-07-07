package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.User;

public interface HeadUserToUserService {

	public abstract void addHeadUserToUser(int idHeadUser, User user);
	
	public abstract List<User> findUserByHeadUser(User headUser);
	
	public abstract User findHeadUserByUser(User user);
	
}
