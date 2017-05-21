package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.User;

public interface UserService {
	
	public abstract void addUser(User user, int idUser);
	public abstract List<User> listAllUser();
	public abstract User findByEmail(String email);
	public abstract User findById(int idUser);
}
