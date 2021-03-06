package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the tbl_departure database table.
 * 
 */

@Entity
@Table(name="departure")
public class Departure implements Serializable {
	

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_departure", unique=true, nullable=false)
	private int idDeparture;
	
	@Column(nullable=false, length=100)
	private String detail;
	
	//bi-directional many-to-one association to OperationType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_country", nullable=false)
	private Country country;

	@Column(name="status", nullable=false)
	private byte status;

	//bi-directional many-to-one association to CurrencyType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_currency_type", nullable=false)
	private CurrencyType currencyType;
	
	@Column(name="price", nullable=true)
	private double price;
	
	
	
	
	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getIdDeparture() {
		return idDeparture;
	}

	public void setIdDeparture(int idDeparture) {
		this.idDeparture = idDeparture;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Departure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departure(int idDeparture, String detail, Country country, byte status, CurrencyType currencyType,
			double price) {
		super();
		this.idDeparture = idDeparture;
		this.detail = detail;
		this.country = country;
		this.status = status;
		this.currencyType = currencyType;
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "Departure [idDeparture=" + idDeparture + ", detail=" + detail + ", country=" + country + ", status="
				+ status + ", currencyType=" + currencyType + ", price=" + price + "]";
	}

	
}
