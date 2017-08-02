package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_currency_exchange database table.
 * 
 */
@Entity
@Table(name="log_currency_exchange")
public class LogCurrencyExchange implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;

	@Column(nullable=false)
	private double buy;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	@Column(name="id_country", nullable=false, length=8)
	private int idCountry;

	@Column(name="id_currency_exchange", nullable=false, length=8)
	private int idCurrencyExchange;

	@Column(name="id_currency_type", nullable=false, length=8)
	private int idCurrencyType;

	@Column(nullable=false)
	private double sell;
	
	

	public LogCurrencyExchange() {
		super();
	}

	public LogCurrencyExchange(Date dateRecord, String actionDetail, int actionUser, double buy, Date date,
			int idCountry, int idCurrencyExchange, int idCurrencyType, double sell) {
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

	public int getActionUser() {
		return this.actionUser;
	}

	public void setActionUser(int actionUser) {
		this.actionUser = actionUser;
	}

	public double getBuy() {
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

	public int getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public int getIdCurrencyExchange() {
		return this.idCurrencyExchange;
	}

	public void setIdCurrencyExchange(int idCurrencyExchange) {
		this.idCurrencyExchange = idCurrencyExchange;
	}

	public int getIdCurrencyType() {
		return this.idCurrencyType;
	}

	public void setIdCurrencyType(int idCurrencyType) {
		this.idCurrencyType = idCurrencyType;
	}

	public double getSell() {
		return this.sell;
	}

	public void setSell(float sell) {
		this.sell = sell;
	}

}