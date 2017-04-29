package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_asseament database table.
 * 
 */
@Entity
@Table(name="log_assessment")
public class LogAssessment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false, length=8)
	private String actionUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date", nullable=false)
	private Date creationDate;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(name="id_assessment", nullable=false, length=8)
	private int idAssessment;

	@Column(name="id_currency_exchange", nullable=false, length=8)
	private int idCurrencyExchange;

	@Column(name="id_sa_client", nullable=false, length=8)
	private int idSaClient;

	@Column(name="id_user", nullable=false, length=8)
	private int idUser;
	


	public LogAssessment() {
		super();
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

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getIdAssessment() {
		return this.idAssessment;
	}

	public void setIdAssessment(int idAssessment) {
		this.idAssessment = idAssessment;
	}

	public int getIdCurrencyExchange() {
		return this.idCurrencyExchange;
	}

	public void setIdCurrencyExchange(int idCurrencyExchange) {
		this.idCurrencyExchange = idCurrencyExchange;
	}

	public int getIdSaClient() {
		return this.idSaClient;
	}

	public void setIdSaClient(int idSaClient) {
		this.idSaClient = idSaClient;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
	public LogAssessment(Date dateRecord, String actionDetail, String actionUser, Date creationDate, String detail,
			int idAssessment, int idCurrencyExchange, int idSaClient, int idUser) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.creationDate = creationDate;
		this.detail = detail;
		this.idAssessment = idAssessment;
		this.idCurrencyExchange = idCurrencyExchange;
		this.idSaClient = idSaClient;
		this.idUser = idUser;
	}
	
	

}