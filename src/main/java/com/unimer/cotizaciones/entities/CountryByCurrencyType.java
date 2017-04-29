package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "country_by_currency_type")
public class CountryByCurrencyType  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@JoinColumn(name = "id_country")
	@Column(name="id_country", unique=true, nullable=false)
	private int idCountry;

	
	@JoinColumn(name = "id_currency_type")	
	@Column(name="id_currency_type", unique=true, nullable=false)
	private int idCurrencyType;


	public int getIdCountry() {
		return idCountry;
	}


	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}


	public int getIdCurrencyType() {
		return idCurrencyType;
	}


	public void setIdCurrencyType(int idCurrencyType) {
		this.idCurrencyType = idCurrencyType;
	}


	public CountryByCurrencyType(int idCountry, int idCurrencyType) {
		super();
		this.idCountry = idCountry;
		this.idCurrencyType = idCurrencyType;
	}


	@Override
	public String toString() {
		return "CountryByCurrencyType [idCountry=" + idCountry + ", idCurrencyType=" + idCurrencyType + "]";
	}



	public CountryByCurrencyType() {
		super();
		
	}
	
	
}
