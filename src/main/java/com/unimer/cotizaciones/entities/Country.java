package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_country database table.
 * 
 */
@Entity
@Table(name="country")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_country", unique=true, nullable=false)
	private int idCountry;

	@Column(nullable=false, length=3)
	private String cod;

	@Column(nullable=false, length=100)
	private String detail;

	public int getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Country(int idCountry, String cod, String detail) {
		super();
		this.idCountry = idCountry;
		this.cod = cod;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Country [idCountry=" + idCountry + ", cod=" + cod + ", detail=" + detail + "]";
	}
}