package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.User;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<User, Serializable>{
	
	public abstract User findByIdUser(int idUser);
	
	@Query("select U from User U where U.email like %:email%")
	public abstract User findByEmail(@Param("email")String email);
	
	public abstract List<User> findByCountry(Country country);
	
}