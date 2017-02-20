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

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the tbl_currency_exchange database table.
 * 
 */
@Entity
@Table(name="tbl_currency_exchange")
public class CurrencyExchange implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_currency_exchange", unique=true, nullable=false, length=8)
	private String idCurrencyExchange;

	@Column(nullable=false)
	private float buy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date date;

	@Column(nullable=false)
	private float sell;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_country", nullable=false)
	private Country country;

	//bi-directional many-to-one association to CurrencyType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_currency_type", nullable=false)
	private CurrencyType currencyType;

	public String getIdCurrencyExchange() {
		return idCurrencyExchange;
	}

	public void setIdCurrencyExchange(String idCurrencyExchange) {
		this.idCurrencyExchange = idCurrencyExchange;
	}

	public float getBuy() {
		return buy;
	}

	public void setBuy(float buy) {
		this.buy = buy;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getSell() {
		return sell;
	}

	public void setSell(float sell) {
		this.sell = sell;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public CurrencyExchange() {
		super();
		this.country = new Country();
		this.currencyType = new CurrencyType();
	}

	public CurrencyExchange(String idCurrencyExchange, float buy, Date date, float sell, Country country, CurrencyType currencyType) {
		super();
		this.idCurrencyExchange = idCurrencyExchange;
		this.buy = buy;
		this.date = date;
		this.sell = sell;
		this.country = country;
		this.currencyType = currencyType;
	}

	@Override
	public String toString() {
		return "CurrencyExchange [idCurrencyExchange=" + idCurrencyExchange + ", buy=" + buy + ", date=" + date
				+ ", sell=" + sell + ", country=" + country + ", currencyType=" + currencyType + "]";
	}
}