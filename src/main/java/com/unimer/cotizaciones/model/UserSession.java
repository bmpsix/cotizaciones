package com.unimer.cotizaciones.model;

public class UserSession {

	
	private int id;
	
	private String mail;
	
	private String detailRol;
	
	private int idCountry;
	
	private String detailCountry;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDetailRol() {
		return detailRol;
	}

	public void setDetailRol(String detailRol) {
		this.detailRol = detailRol;
	}

	public int getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public String getDetailCountry() {
		return detailCountry;
	}

	public void setDetailCountry(String detailCountry) {
		this.detailCountry = detailCountry;
	}

	

	public UserSession(int id, String mail, String detailRol, int idCountry, String detailCountry) {
		super();
		this.id = id;
		this.mail = mail;
		this.detailRol = detailRol;
		this.idCountry = idCountry;
		this.detailCountry = detailCountry;
	}

	public UserSession() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserSession [id=" + id + ", mail=" + mail + ", detailRol=" + detailRol + ", idCountry=" + idCountry
				+ ", detailCountry=" + detailCountry + "]";
	}
	
	
	
	
}
