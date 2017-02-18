package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.entities.Consecutive;

public interface UserService {

	public abstract Consecutive getConsecutive();
	
	public abstract User addUser(User user);
	
	public abstract List<User> listAllUser();
	
	public abstract User findById(String idUser);
	
}
