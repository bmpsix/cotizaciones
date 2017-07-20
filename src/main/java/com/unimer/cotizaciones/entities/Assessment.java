package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tbl_asseament database table.
 * 
 */
@Entity
@Table(name="assessment")
public class Assessment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_assessment", unique=true, nullable=false)
	private int idAssessment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date", nullable=false)
	private Date creationDate;

	@Column(nullable=false, length=100)
	private String detail;

	//bi-directional many-to-one association to CurrencyExchange
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_currency_exchange", nullable=false)
	private CurrencyExchange currencyExchange;*/
	
	@Column(name="currency_exchange", nullable=false)
	private double currencyExchange;

	//bi-directional many-to-one association to SaClient
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_sa_client", nullable=false)
	private SaClient saClient;

	//bi-directional many-to-one association to TblUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user", nullable=false)
	private User user;
	
	//bi-directional many-to-one association to TblUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user_assigned", nullable=false)
	private User userAssigned;
	
	//bi-directional many-to-one association to TblUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_status", nullable=false)
	private Status status;
	

	public int getIdAssessment() {
		return idAssessment;
	}

	public void setIdAssessment(int idAssessment) {
		this.idAssessment = idAssessment;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public double getCurrencyExchange() {
		return currencyExchange;
	}

	public void setCurrencyExchange(double currencyExchange) {
		this.currencyExchange = currencyExchange;
	}

	public SaClient getSaClient() {
		return saClient;
	}

	public void setSaClient(SaClient saClient) {
		this.saClient = saClient;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUserAssigned() {
		return userAssigned;
	}

	public void setUserAssigned(User userAssigned) {
		this.userAssigned = userAssigned;
	}


	public Assessment(int idAssessment, Date creationDate, String detail, double currencyExchange,
			SaClient saClient, User user, User userAssigned, Status status) {
		super();
		this.idAssessment = idAssessment;
		this.creationDate = creationDate;
		this.detail = detail;
		this.currencyExchange = currencyExchange;
		this.saClient = saClient;
		this.user = user;
		this.userAssigned = userAssigned;
		this.status = status;
	}

	public Assessment() {
		super();
	}

	@Override
	public String toString() {
		return "Assessment [idAssessment=" + idAssessment + ", creationDate=" + creationDate + ", detail=" + detail
				+ ", currencyExchange=" + currencyExchange + ", saClient=" + saClient + ", user=" + user
				+ ", userAssigned=" + userAssigned + ", status=" + status + "]";
	}
	
	


	
	
	

	
	
}

	