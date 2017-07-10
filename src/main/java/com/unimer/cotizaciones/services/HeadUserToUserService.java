package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.HeadUserToUser;
import com.unimer.cotizaciones.entities.User;

public interface HeadUserToUserService {

	public abstract void addHeadUserToUser(int idHeadUser, User user);
	
	public abstract List<HeadUserToUser> findUserByHeadUser(User headUser);
	
	public abstract HeadUserToUser findByUser(User user);
	
	public abstract List<HeadUserToUser> findHeadUserToUser();
	
	
	public abstract void delete(HeadUserToUser headUserToUser);
}
