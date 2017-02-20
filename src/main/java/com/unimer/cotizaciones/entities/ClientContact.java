package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_client_contact database table.
 * 
 */
@Entity
@Table(name="tbl_client_contact")
public class ClientContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_client_contact", unique=true, nullable=false, length=8)
	private String idClientContact;

	@Column(nullable=false, length=20)
	private String email;

	@Column(nullable=false, length=50)
	private String ext;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false, length=15)
	private String phone;

	@Column(nullable=false)
	private byte status;

	//bi-directional many-to-one association to Client
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_client", nullable=false)
	private Client client;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_country", nullable=false)
	private Country country;

	public String getIdClientContact() {
		return idClientContact;
	}

	public void setIdClientContact(String idClientContact) {
		this.idClientContact = idClientContact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
	
	public ClientContact() {
		super();
		this.client = new Client();
		this.country = new Country();
	}

	public ClientContact(String idClientContact, String email, String ext, String name, String phone, byte status,
			Client client, Country country) {
		super();
		this.idClientContact = idClientContact;
		this.email = email;
		this.ext = ext;
		this.name = name;
		this.phone = phone;
		this.status = status;
		this.client = client;
		this.country = country;
	}

	@Override
	public String toString() {
		return "ClientContact [idClientContact=" + idClientContact + ", email=" + email + ", ext=" + ext + ", name="
				+ name + ", phone=" + phone + ", status=" + status + ", client=" + client + ", country=" + country
				+ "]";
	}
	
	


}