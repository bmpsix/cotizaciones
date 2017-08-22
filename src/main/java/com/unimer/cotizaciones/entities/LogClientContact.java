package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_client_contact database table.
 * 
 */
@Entity
@Table(name="log_client_contact")
public class LogClientContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;

	@Column(nullable=false, length=150)
	private String email;

	@Column(nullable=false, length=50)
	private String ext;

	@Column(name="id_client", nullable=false, length=8)
	private int idClient;

	@Column(name="id_client_contact", nullable=false, length=8)
	private int idClientContact;

	@Column(name="id_country", nullable=false, length=8)
	private int idCountry;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false, length=15)
	private String phone;

	@Column(nullable=false)
	private byte status;

	public LogClientContact() {
	}

	public Date getDateRecord() {
		return this.dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	public String getActionDetail() {
		return this.actionDetail;
	}

	public void setActionDetail(String actionDetail) {
		this.actionDetail = actionDetail;
	}

	public int getActionUser() {
		return this.actionUser;
	}

	public void setActionUser(int actionUser) {
		this.actionUser = actionUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getIdClient() {
		return this.idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdClientContact() {
		return this.idClientContact;
	}

	public void setIdClientContact(int idClientContact) {
		this.idClientContact = idClientContact;
	}

	public int getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public LogClientContact(Date dateRecord, String actionDetail, int actionUser, String email, String ext,
			int idClient, int idClientContact, int idCountry, String name, String phone, byte status) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.email = email;
		this.ext = ext;
		this.idClient = idClient;
		this.idClientContact = idClientContact;
		this.idCountry = idCountry;
		this.name = name;
		this.phone = phone;
		this.status = status;
	}

	
	
	

}