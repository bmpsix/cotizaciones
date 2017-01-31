package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tbl_user database table.
 * 
 */
@Entity
@Table(name="tbl_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user", unique=true, nullable=false, length=8)
	private String idUser;

	@Column(name="account_bank", nullable=false, length=50)
	private String accountBank;

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

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_country", nullable=false)
	private Country country;

	//bi-directional many-to-one association to Rol
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_rol", nullable=false)
	private Rol rol;

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public double getCommitionAmount() {
		return commitionAmount;
	}

	public void setCommitionAmount(double commitionAmount) {
		this.commitionAmount = commitionAmount;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public byte getCredentialExpired() {
		return credentialExpired;
	}

	public void setCredentialExpired(byte credentialExpired) {
		this.credentialExpired = credentialExpired;
	}

	public Date getCredentialExpiredAt() {
		return credentialExpiredAt;
	}

	public void setCredentialExpiredAt(Date credentialExpiredAt) {
		this.credentialExpiredAt = credentialExpiredAt;
	}

	public byte getExpired() {
		return expired;
	}

	public void setExpired(byte expired) {
		this.expired = expired;
	}

	public byte getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(byte expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Date getLastLoggin() {
		return lastLoggin;
	}

	public void setLastLoggin(Date lastLoggin) {
		this.lastLoggin = lastLoggin;
	}

	public Date getLastModification() {
		return lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMidname() {
		return midname;
	}

	public void setMidname(String midname) {
		this.midname = midname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getUseCommition() {
		return useCommition;
	}

	public void setUseCommition(int useCommition) {
		this.useCommition = useCommition;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
		this.country = new Country();
		this.rol = new Rol();
	}

	public User(String idUser, String accountBank, double commitionAmount, String confirmationToken, Date creationDate,
			byte credentialExpired, Date credentialExpiredAt, byte expired, byte expiredAt, Date lastLoggin,
			Date lastModification, String lastname, String midname, String password, byte status, int useCommition,
			String username, Country country, Rol rol) {
		super();
		this.idUser = idUser;
		this.accountBank = accountBank;
		this.commitionAmount = commitionAmount;
		this.confirmationToken = confirmationToken;
		this.creationDate = creationDate;
		this.credentialExpired = credentialExpired;
		this.credentialExpiredAt = credentialExpiredAt;
		this.expired = expired;
		this.expiredAt = expiredAt;
		this.lastLoggin = lastLoggin;
		this.lastModification = lastModification;
		this.lastname = lastname;
		this.midname = midname;
		this.password = password;
		this.status = status;
		this.useCommition = useCommition;
		this.username = username;
		this.country = country;
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", accountBank=" + accountBank + ", commitionAmount=" + commitionAmount
				+ ", confirmationToken=" + confirmationToken + ", creationDate=" + creationDate + ", credentialExpired="
				+ credentialExpired + ", credentialExpiredAt=" + credentialExpiredAt + ", expired=" + expired
				+ ", expiredAt=" + expiredAt + ", lastLoggin=" + lastLoggin + ", lastModification=" + lastModification
				+ ", lastname=" + lastname + ", midname=" + midname + ", password=" + password + ", status=" + status
				+ ", useCommition=" + useCommition + ", username=" + username + ", country=" + country + ", rol=" + rol
				+ "]";
	}
}