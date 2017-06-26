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
 * The persistent class for the tbl_client database table.
 * 
 */
@Entity
@Table(name="settings")
public class Settings  implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_settings", unique=true, nullable=false)
	private int idSettings;
	
	@Column(name="aporte_fijo", nullable=false)
	private double aporteFijo;
	
	@Column(nullable=false)
	private double factor1;
	
	@Column(nullable=false)
	private double factor2;
	
	@Column(name="imprevisto", nullable=true)
	private double imprevisto;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_country", nullable=false)
	private Country country;
	
	//bi-directional many-to-one association to CurrencyType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_currency_type_Fa", nullable=false)
	private CurrencyType currencyTypeFavorite;

	//bi-directional many-to-one association to CurrencyType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_currency_type_In", nullable=false)
	private CurrencyType currencyTypeInternational;

	public int getIdSettings() {
		return idSettings;
	}

	public void setIdSettings(int idSettings) {
		this.idSettings = idSettings;
	}

	public double getAporteFijo() {
		return aporteFijo;
	}

	public void setAporteFijo(double aporteFijo) {
		this.aporteFijo = aporteFijo;
	}

	public double getFactor1() {
		return factor1;
	}

	public void setFactor1(double factor1) {
		this.factor1 = factor1;
	}

	public double getFactor2() {
		return factor2;
	}

	public void setFactor2(double factor2) {
		this.factor2 = factor2;
	}

	public double getImprevisto() {
		return imprevisto;
	}

	public void setImprevisto(double imprevisto) {
		this.imprevisto = imprevisto;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public CurrencyType getCurrencyTypeFavorite() {
		return currencyTypeFavorite;
	}

	public void setCurrencyTypeFavorite(CurrencyType currencyTypeFavorite) {
		this.currencyTypeFavorite = currencyTypeFavorite;
	}

	public CurrencyType getCurrencyTypeInternational() {
		return currencyTypeInternational;
	}

	public void setCurrencyTypeInternational(CurrencyType currencyTypeInternational) {
		this.currencyTypeInternational = currencyTypeInternational;
	}
	

	public Settings(int idSettings, double aporteFijo, double factor1, double factor2, double imprevisto,
			Country country, CurrencyType currencyTypeFavorite, CurrencyType currencyTypeInternational) {
		super();
		this.idSettings = idSettings;
		this.aporteFijo = aporteFijo;
		this.factor1 = factor1;
		this.factor2 = factor2;
		this.imprevisto = imprevisto;
		this.country = country;
		this.currencyTypeFavorite = currencyTypeFavorite;
		this.currencyTypeInternational = currencyTypeInternational;
	}

	public Settings() {
		super();
	}
	
	@Override
	public String toString() {
		return "Settings [idSettings=" + idSettings + ", aporteFijo=" + aporteFijo + ", factor1=" + factor1
				+ ", factor2=" + factor2 + ", imprevisto=" + imprevisto + ", country=" + country
				+ ", currencyTypeFavorite=" + currencyTypeFavorite + ", currencyTypeInternational="
				+ currencyTypeInternational + "]";
	}

}

