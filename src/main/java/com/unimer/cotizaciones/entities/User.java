package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.unimer.cotizaciones.entities.User;

/**
 * The persistent class for the tbl_user database table.
 * 
 */
@Entity
@Table(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user", unique=true, nullable=false)
	private int idUser;
	
	@Column(name="account_bank", nullable=false, length=50)
	private String accountBank;

	@Column(name="commission_amount", nullable=false)
	private double commissionAmount;

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expired_at", nullable=false)
	private Date expiredAt;

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

	@Column(nullable=false)
	private String password;

	@Column(nullable=false)
	private boolean status;

	@Column(name="use_commission", nullable=false)
	private int useCommission;

	@Column(nullable=false, length=50)
	private String name;
	
	@Column(nullable=false)
	private String email;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_country", nullable=false)
	private Country country;
	
	//bi-directional many-to-one association to Rol
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> rol = new HashSet<UserRole>();
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
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

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
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
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setRol(Set<UserRole> rol) {
		this.rol = rol;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public double getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public int getUseCommission() {
		return useCommission;
	}

	public void setUseCommission(int useCommission) {
		this.useCommission = useCommission;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserRole> getRol() {
		return rol;
	}
	
	

	public User() {
		super();
	}

	public User(String lastname, String password, Country country) {
		this.lastname = lastname;
		this.password = password;
		this.country = country;
	}

	public User(String lastname, String password, boolean status, String name, String email, Country country) {
		super();
		this.lastname = lastname;
		this.password = password;
		this.status = status;
		this.name = name;
		this.email = email;
		this.country = country;
	}

	

	public User(String password, boolean status, String name, Set<UserRole> rol) {
		super();
		this.password = password;
		this.status = status;
		this.name = name;
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", accountBank=" + accountBank + ", commissionAmount=" + commissionAmount
				+ ", confirmationToken=" + confirmationToken + ", creationDate=" + creationDate + ", credentialExpired="
				+ credentialExpired + ", credentialExpiredAt=" + credentialExpiredAt + ", expired=" + expired
				+ ", expiredAt=" + expiredAt + ", lastLoggin=" + lastLoggin + ", lastModification=" + lastModification
				+ ", lastname=" + lastname + ", midname=" + midname + ", password=" + password + ", status=" + status
				+ ", useCommission=" + useCommission + ", username=" + name + ", email=" + email + ", country="
				+ country + ", rol=" + rol + "]";
	}
	

	
	


	
	

	
	




	
	

	
	
}