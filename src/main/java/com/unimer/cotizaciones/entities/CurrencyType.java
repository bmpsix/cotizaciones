package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_currency_type database table.
 * 
 */
@Entity
@Table(name="currency_type")
public class CurrencyType implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_currency_type", unique=true, nullable=false)
	private int idCurrencyType;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(nullable=false)
	private byte favorite;

	@Column(nullable=false)
	private byte status;

	@Column(nullable=false, length=3)
	private String symbol;

	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "currencyTypes")
	private Set<Country> countrys;
	
	
	public int getIdCurrencyType() {
		return idCurrencyType;
	}

	public void setIdCurrencyType(int idCurrencyType) {
		this.idCurrencyType = idCurrencyType;
	}
	

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public byte getFavorite() {
		return favorite;
	}

	public void setFavorite(byte favorite) {
		this.favorite = favorite;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public Set<Country> getCountrys() {
		return countrys;
	}

	public void setCountrys(Set<Country> countrys) {
		this.countrys = countrys;
	}




	public CurrencyType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrencyType(int idCurrencyType, String detail, byte favorite, byte status, String symbol) {
		super();
		this.idCurrencyType = idCurrencyType;
		this.detail = detail;
		this.favorite = favorite;
		this.status = status;
		this.symbol = symbol;
	}
	

	public CurrencyType(int idCurrencyType, String detail, byte favorite, byte status, String symbol,
			Set<Country> countrys) {
		super();
		this.idCurrencyType = idCurrencyType;
		this.detail = detail;
		this.favorite = favorite;
		this.status = status;
		this.symbol = symbol;
		this.countrys = countrys;
	}

	@Override
	public String toString() {
		return "CurrencyType [idCurrencyType=" + idCurrencyType + ", detail=" + detail + ", favorite=" + favorite
				+ ", status=" + status + ", symbol=" + symbol + "]";
	}
}