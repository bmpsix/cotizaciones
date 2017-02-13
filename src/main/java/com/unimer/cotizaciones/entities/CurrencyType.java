package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_currency_type database table.
 * 
 */
@Entity
@Table(name="tbl_currency_type")
public class CurrencyType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_curerncy_type", unique=true, nullable=false, length=8)
	private String idCurrencyType;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(nullable=false)
	private byte favorite;

	@Column(nullable=false)
	private byte status;

	@Column(nullable=false, length=1)
	private String symbol;

	public String getIdCurrencyType() {
		return idCurrencyType;
	}

	public void setIdCurrencyType(String idCurerncyType) {
		this.idCurrencyType = idCurerncyType;
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

	public CurrencyType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrencyType(String idCurerncyType, String detail, byte favorite, byte status, String symbol) {
		super();
		this.idCurrencyType = idCurerncyType;
		this.detail = detail;
		this.favorite = favorite;
		this.status = status;
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "CurrencyType [idCurerncyType=" + idCurrencyType + ", detail=" + detail + ", favorite=" + favorite
				+ ", status=" + status + ", symbol=" + symbol + "]";
	}
}