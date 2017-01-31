package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_client_contact database table.
 * 
 */
@Entity
@Table(name="tbl_log_client_contact")
public class LogClientContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false, length=8)
	private String actionUser;

	@Column(nullable=false, length=20)
	private String email;

	@Column(nullable=false, length=50)
	private String ext;

	@Column(name="id_client", nullable=false, length=8)
	private String idClient;

	@Column(name="id_client_contact", nullable=false, length=8)
	private String idClientContact;

	@Column(name="id_country", nullable=false, length=8)
	private String idCountry;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false, length=15)
	private String phone;

	@Column(nullable=false)
	private byte[] status;

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

	public String getActionUser() {
		return this.actionUser;
	}

	public void setActionUser(String actionUser) {
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

	public String getIdClient() {
		return this.idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getIdClientContact() {
		return this.idClientContact;
	}

	public void setIdClientContact(String idClientContact) {
		this.idClientContact = idClientContact;
	}

	public String getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(String idCountry) {
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

	public byte[] getStatus() {
		return this.status;
	}

	public void setStatus(byte[] status) {
		this.status = status;
	}

}