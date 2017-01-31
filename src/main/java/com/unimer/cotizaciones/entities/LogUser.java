package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_user database table.
 * 
 */
@Entity
@Table(name="tbl_log_user")
public class LogUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="account_bank", nullable=false, length=50)
	private String accountBank;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false, length=8)
	private String actionUser;

	@Column(name="commition_amount", nullable=false)
	private double commitionAmount;

	@Column(name="confirmation_token", nullable=false, length=50)
	private String confirmationToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date", nullable=false)
	private Date creationDate;

	@Column(name="credential_expired", nullable=false)
	private byte credentialExpired;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="credential_expired_at", nullable=false)
	private Date credentialExpiredAt;

	@Column(nullable=false)
	private byte expired;

	@Column(name="expired_at", nullable=false)
	private byte expiredAt;

	@Column(name="id_country", nullable=false, length=8)
	private String idCountry;

	@Column(name="id_rol", nullable=false, length=8)
	private String idRol;

	@Column(name="id_user", nullable=false, length=8)
	private String idUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_loggin", nullable=false)
	private Date lastLoggin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modification", nullable=false)
	private Date lastModification;

	@Column(nullable=false, length=50)
	private String lastname;

	@Column(nullable=false, length=50)
	private String midname;

	@Column(nullable=false, length=50)
	private String password;

	@Column(nullable=false)
	private byte status;

	@Column(name="use_commition", nullable=false)
	private int useCommition;

	@Column(nullable=false, length=50)
	private String username;

	public LogUser() {
	}

	public Date getDateRecord() {
		return this.dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	public String getAccountBank() {
		return this.accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
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

	public double getCommitionAmount() {
		return this.commitionAmount;
	}

	public void setCommitionAmount(double commitionAmount) {
		this.commitionAmount = commitionAmount;
	}

	public String getConfirmationToken() {
		return this.confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public byte getCredentialExpired() {
		return this.credentialExpired;
	}

	public void setCredentialExpired(byte credentialExpired) {
		this.credentialExpired = credentialExpired;
	}

	public Date getCredentialExpiredAt() {
		return this.credentialExpiredAt;
	}

	public void setCredentialExpiredAt(Date credentialExpiredAt) {
		this.credentialExpiredAt = credentialExpiredAt;
	}

	public byte getExpired() {
		return this.expired;
	}

	public void setExpired(byte expired) {
		this.expired = expired;
	}

	public byte getExpiredAt() {
		return this.expiredAt;
	}

	public void setExpiredAt(byte expiredAt) {
		this.expiredAt = expiredAt;
	}

	public String getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(String idCountry) {
		this.idCountry = idCountry;
	}

	public String getIdRol() {
		return this.idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Date getLastLoggin() {
		return this.lastLoggin;
	}

	public void setLastLoggin(Date lastLoggin) {
		this.lastLoggin = lastLoggin;
	}

	public Date getLastModification() {
		return this.lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMidname() {
		return this.midname;
	}

	public void setMidname(String midname) {
		this.midname = midname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getUseCommition() {
		return this.useCommition;
	}

	public void setUseCommition(int useCommition) {
		this.useCommition = useCommition;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}