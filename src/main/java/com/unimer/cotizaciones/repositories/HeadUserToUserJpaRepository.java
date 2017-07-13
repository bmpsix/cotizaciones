package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.HeadUserToUser;
import com.unimer.cotizaciones.entities.User;

@Repository("headUserToUserJpaRepository")
public interface HeadUserToUserJpaRepository extends JpaRepository<HeadUserToUser, Serializable>{

	public abstract List<HeadUserToUser> findUserByHeadUser(User headUser);
	public abstract HeadUserToUser findHeadUserToUserByUser(User user);
	
}
