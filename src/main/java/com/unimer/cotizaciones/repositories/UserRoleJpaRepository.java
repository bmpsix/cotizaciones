package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.UserRole;


@Repository("userRoleJpaRepository")
public interface UserRoleJpaRepository extends JpaRepository<UserRole, Serializable>{
	
	public abstract UserRole findByUserRoleId(int role);
	
	public abstract UserRole findByRole(String detail);
	
	
}