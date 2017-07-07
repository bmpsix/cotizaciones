package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="headUser_To_User")
public class HeadUserToUser implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_headUserToUser", unique=true, nullable=false)
	private int idHeadUserToUser;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_head_user", nullable=false)
	private User headUser;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user", nullable=false)
	private User user;

	
	
	public int getIdHeadUserToUser() {
		return idHeadUserToUser;
	}


	public void setIdHeadUserToUser(int idHeadUserToUser) {
		this.idHeadUserToUser = idHeadUserToUser;
	}


	public User getHeadUser() {
		return headUser;
	}


	public void setHeadUser(User headUser) {
		this.headUser = headUser;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "HeadUserToUser [idHeadUserToUser=" + idHeadUserToUser + ", headUser=" + headUser + ", user=" + user
				+ "]";
	}


	

	public HeadUserToUser() {
		super();
	}


	public HeadUserToUser(int idHeadUserToUser, User headUser, User user) {
		super();
		this.idHeadUserToUser = idHeadUserToUser;
		this.headUser = headUser;
		this.user = user;
	}


	public HeadUserToUser(User headUser, User user) {
		super();
		this.headUser = headUser;
		this.user = user;
	}

	
	
}
