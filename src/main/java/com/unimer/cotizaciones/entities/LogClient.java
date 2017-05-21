package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_client database table.
 * 
 */
@Entity
@Table(name="log_client")
public class LogClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;

	@Column(length=100)
	private String detail;

	@Column(length=20)
	private String email;

	@Column(length=20)
	private String fax;

	@Column(name="id_client", nullable=false, length=8)
	private int idClient;

	@Column(name="id_client_type", length=8)
	private int idClientType;

	@Column(name="id_country", length=8)
	private int idCountry;

	@Column(name="id_sa_client", length=8)
	private int idSaClient;

	@Column(length=15)
	private String phone;

	
	
	@Column(name="status", nullable=false)
	private byte status;
	
	public LogClient() {
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

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getIdClient() {
		return this.idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdClientType() {
		return this.idClientType;
	}

	public void setIdClientType(int idClientType) {
		this.idClientType = idClientType;
	}

	public int getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public int getIdSaClient() {
		return this.idSaClient;
	}

	public void setIdSaClient(int idSaClient) {
		this.idSaClient = idSaClient;
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

	public LogClient(Date dateRecord, String actionDetail, int actionUser, String detail, String email, String fax,
			int idClient, int idClientType, int idCountry, int idSaClient, String phone, byte status) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.detail = detail;
		this.email = email;
		this.fax = fax;
		this.idClient = idClient;
		this.idClientType = idClientType;
		this.idCountry = idCountry;
		this.idSaClient = idSaClient;
		this.phone = phone;
		this.status = status;
	}

	
	
	
}