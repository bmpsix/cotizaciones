package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
	@JoinTable(name = "country_by_currency_type", joinColumns =@JoinColumn(name = "id_country", referencedColumnName = "id_country")  , inverseJoinColumns =@JoinColumn(name = "id_currency_type", referencedColumnName = "id_currency_type"))
	
	private Set<CurrencyType> currencyTypes;
	
	
	
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
	
	public Set<CurrencyType> getCurrencyType() {
		return currencyTypes;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyTypes.add(currencyType);
	}

	public void dropCurrencyType(CurrencyType currencyType)
	{
		this.currencyTypes.remove(currencyType);
	}
	

	public Country(int idCountry, String cod, String detail) {
		super();
		this.idCountry = idCountry;
		this.cod = cod;
		this.detail = detail;
	}
	
	

	public Country(int idCountry, String cod, String detail, Set<CurrencyType> currencyTypes) {
		super();
		this.idCountry = idCountry;
		this.cod = cod;
		this.detail = detail;
		this.currencyTypes = currencyTypes;
	}

	@Override
	public String toString() {
		return "Country [idCountry=" + idCountry + ", cod=" + cod + ", detail=" + detail + ", currencyTypes="
				+ currencyTypes + "]";
	}
	
	
	
}