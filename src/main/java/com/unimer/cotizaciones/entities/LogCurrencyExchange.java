package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_currency_exchange database table.
 * 
 */
@Entity
@Table(name="tbl_log_currency_exchange")
public class LogCurrencyExchange implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false, length=8)
	private String actionUser;

	@Column(nullable=false)
	private float buy;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	@Column(name="id_country", nullable=false, length=8)
	private String idCountry;

	@Column(name="id_currency_exchange", nullable=false, length=8)
	private String idCurrencyExchange;

	@Column(name="id_currency_type", nullable=false, length=8)
	private String idCurrencyType;

	@Column(nullable=false)
	private float sell;
	
	

	public LogCurrencyExchange() {
		super();
	}

	public LogCurrencyExchange(Date dateRecord, String actionDetail, String actionUser, float buy, Date date,
			String idCountry, String idCurrencyExchange, String idCurrencyType, float sell) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.buy = buy;
		this.date = date;
		this.idCountry = idCountry;
		this.idCurrencyExchange = idCurrencyExchange;
		this.idCurrencyType = idCurrencyType;
		this.sell = sell;
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

	public float getBuy() {
		return this.buy;
	}

	public void setBuy(float buy) {
		this.buy = buy;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(String idCountry) {
		this.idCountry = idCountry;
	}

	public String getIdCurrencyExchange() {
		return this.idCurrencyExchange;
	}

	public void setIdCurrencyExchange(String idCurrencyExchange) {
		this.idCurrencyExchange = idCurrencyExchange;
	}

	public String getIdCurrencyType() {
		return this.idCurrencyType;
	}

	public void setIdCurrencyType(String idCurrencyType) {
		this.idCurrencyType = idCurrencyType;
	}

	public float getSell() {
		return this.sell;
	}

	public void setSell(float sell) {
		this.sell = sell;
	}

}