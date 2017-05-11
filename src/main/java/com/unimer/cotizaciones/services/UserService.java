package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.User;

public interface UserService {
	
	public abstract void addUser(User user);
	public abstract List<User> listAllUser();
	public abstract User findById(int idUser);
	
}
