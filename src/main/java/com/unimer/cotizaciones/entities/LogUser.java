package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_user database table.
 * 
 */
@Entity
@Table(name="log_user")
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

	@Column(name="id_country", nullable=false, length=8)
	private int idCountry;

	@Column(name="id_role", nullable=false, length=8)
	private int idRole;
	
	@Column(name="id_user", nullable=false, length=8)
	private int idUser;

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

	@Column(name="use_commission", nullable=false)
	private int useCommission;

	@Column(nullable=false, length=50)
	private String username;
	
	@Column(nullable=false)
	private String email;

	public LogUser() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getExpiredAt() {
		return this.expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

	public int getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}


	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
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



	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public LogUser(Date dateRecord, String accountBank, String actionDetail, String actionUser, double commissionAmount,
			String confirmationToken, Date creationDate, byte credentialExpired, Date credentialExpiredAt, byte expired,
			Date expiredAt, int idCountry, int idUser, Date lastLoggin, Date lastModification,
			String lastname, String midname, String password, byte status, int useCommission, String username) {
		super();
		this.dateRecord = dateRecord;
		this.accountBank = accountBank;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.commissionAmount = commissionAmount;
		this.confirmationToken = confirmationToken;
		this.creationDate = creationDate;
		this.credentialExpired = credentialExpired;
		this.credentialExpiredAt = credentialExpiredAt;
		this.expired = expired;
		this.expiredAt = expiredAt;
		this.idCountry = idCountry;
		this.idUser = idUser;
		this.lastLoggin = lastLoggin;
		this.lastModification = lastModification;
		this.lastname = lastname;
		this.midname = midname;
		this.password = password;
		this.status = status;
		this.useCommission = useCommission;
		this.username = username;
	}

	@Override
	public String toString() {
		return "LogUser [dateRecord=" + dateRecord + ", accountBank=" + accountBank + ", actionDetail=" + actionDetail
				+ ", actionUser=" + actionUser + ", commissionAmount=" + commissionAmount + ", confirmationToken="
				+ confirmationToken + ", creationDate=" + creationDate + ", credentialExpired=" + credentialExpired
				+ ", credentialExpiredAt=" + credentialExpiredAt + ", expired=" + expired + ", expiredAt=" + expiredAt
				+ ", idCountry=" + idCountry + ", idUser=" + idUser + ", lastLoggin=" + lastLoggin
				+ ", lastModification=" + lastModification + ", lastname=" + lastname + ", midname=" + midname
				+ ", password=" + password + ", status=" + status + ", useCommission=" + useCommission + ", username="
				+ username + ", email=" + email + "]";
	}

	



	
	
	

}