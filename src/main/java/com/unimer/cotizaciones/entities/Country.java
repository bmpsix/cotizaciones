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
	
	@Column(nullable=false)
	private double iva;
	
	@Column(nullable=false)
	private double tranference_value;
	
	@Column(nullable=false)
	private double remission;
	
	@Column(nullable=false)
	private boolean except_Sale;
	
	@Column(nullable=false)
	private boolean apply_for_charge;

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL )
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
	
	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTranference_value() {
		return tranference_value;
	}

	public void setTranference_value(double tranference_value) {
		this.tranference_value = tranference_value;
	}

	public double getRemission() {
		return remission;
	}

	public void setRemission(double remission) {
		this.remission = remission;
	}

	public boolean isExcept_Sale() {
		return except_Sale;
	}

	public void setExcept_Sale(boolean except_Sale) {
		this.except_Sale = except_Sale;
	}

	public boolean isApply_for_charge() {
		return apply_for_charge;
	}

	public void setApply_for_charge(boolean apply_for_charge) {
		this.apply_for_charge = apply_for_charge;
	}

	public Set<CurrencyType> getCurrencyTypes() {
		return currencyTypes;
	}

	public void setCurrencyTypes(Set<CurrencyType> currencyTypes) {
		this.currencyTypes = currencyTypes;
	}

	public Country(int idCountry, String cod, String detail, double iva, double tranference_value, double remission,
			boolean except_Sale, boolean apply_for_charge, Set<CurrencyType> currencyTypes) {
		super();
		this.idCountry = idCountry;
		this.cod = cod;
		this.detail = detail;
		this.iva = iva;
		this.tranference_value = tranference_value;
		this.remission = remission;
		this.except_Sale = except_Sale;
		this.apply_for_charge = apply_for_charge;
		this.currencyTypes = currencyTypes;
	}


	@Override
	public String toString() {
		return "Country [idCountry=" + idCountry + ", cod=" + cod + ", detail=" + detail + ", iva=" + iva
				+ ", tranference_value=" + tranference_value + ", remission=" + remission + ", except_Sale="
				+ except_Sale + ", apply_for_charge=" + apply_for_charge + ", currencyTypes=" + currencyTypes + "]";
	}
	
	


	
	
	
}