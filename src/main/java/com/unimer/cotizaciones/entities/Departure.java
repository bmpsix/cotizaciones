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
	
	public Departure(int idDeparture, String detail, Country country) {
		super();
		this.idDeparture = idDeparture;
		this.detail = detail;
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "Operation [idDeparture=" + idDeparture + ", detail=" + detail + ", country=" + country+ "]";
	}	
	
}
