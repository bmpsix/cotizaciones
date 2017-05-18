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


/**
 * The persistent class for the tbl_client database table.
 * 
 */
@Entity
@Table(name="client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_client", unique=true, nullable=false)
	private int idClient;

	@Column(length=100, nullable=false)
	private String detail;

	@Column(length=50, nullable=false)
	private String email;

	@Column(length=15, nullable=false)
	private String fax;

	@Column(length=15, nullable=false)
	private String phone;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_country", nullable=false)
	private Country country;

	//bi-directional many-to-one association to SaClient
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_sa_client", nullable=false)
	private SaClient saClient;

	//bi-directional many-to-one association to ClientType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_client_type", nullable=false)
	private ClientType clientType;
	
	@Column(name="status", nullable=false)
	private byte status;

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public SaClient getSaClient() {
		return saClient;
	}

	public void setSaClient(SaClient saClient) {
		this.saClient = saClient;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	
	
	
	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Client() {
		super();
		this.country = new Country();
		this.saClient = new SaClient();
		this.clientType = new ClientType();
	}

	public Client(int idClient, String detail, String email, String fax, String phone, Country country,
			SaClient saClient, ClientType clientType, byte status) {
		super();
		this.idClient = idClient;
		this.detail = detail;
		this.email = email;
		this.fax = fax;
		this.phone = phone;
		this.country = country;
		this.saClient = saClient;
		this.clientType = clientType;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", detail=" + detail + ", email=" + email + ", fax=" + fax + ", phone="
				+ phone + ", country=" + country + ", saClient=" + saClient + ", clientType=" + clientType + ", status="
				+ status + "]";
	}

	
}