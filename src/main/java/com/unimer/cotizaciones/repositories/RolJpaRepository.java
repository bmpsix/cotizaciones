package com.unimer.cotizaciones.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Rol;

@Repository("rolJpaRepository")
public interface RolJpaRepository extends JpaRepository<Rol, Serializable>{
	
	//public abstract List<Rol> findRolesByStatus(byte status);
	
	public abstract Rol findByDetail(String detail);
	
	public abstract Rol findByIdRol(String idRol);
}