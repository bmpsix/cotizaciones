package com.unimer.cotizaciones.services;

import java.util.List;
import com.unimer.cotizaciones.entities.UserRole;

public interface UserRoleService {

	public abstract UserRole addRol(UserRole rol);
	
	public abstract List<UserRole> listAllRol();
	
	public abstract UserRole findByUserRoleId(int idRole);
	
	public abstract UserRole findByRole(String role);
	
}
