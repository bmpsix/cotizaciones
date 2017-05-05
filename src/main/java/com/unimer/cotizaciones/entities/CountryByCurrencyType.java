package com.unimer.cotizaciones.entities;



public class CountryByCurrencyType {

	
private int idCountry;
	
	private String cod;

	private String countryDetail;

	
	private int idCurrencyType;

	private String currencyTypeDetail;

	private byte favorite;

	private byte status;

	private String symbol;
	
	
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

	public String getCountryDetail() {
		return countryDetail;
	}

	public void setCountryDetail(String countryDetail) {
		this.countryDetail = countryDetail;
	}

	public int getIdCurrencyType() {
		return idCurrencyType;
	}

	public void setIdCurrencyType(int idCurrencyType) {
		this.idCurrencyType = idCurrencyType;
	}

	public String getCurrencyTypeDetail() {
		return currencyTypeDetail;
	}

	public void setCurrencyTypeDetail(String currencyTypeDetail) {
		this.currencyTypeDetail = currencyTypeDetail;
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

	public CountryByCurrencyType(int idCountry, String cod, String countryDetail, int idCurrencyType,
			String currencyTypeDetail, byte favorite, byte status, String symbol) {
		super();
		this.idCountry = idCountry;
		this.cod = cod;
		this.countryDetail = countryDetail;
		this.idCurrencyType = idCurrencyType;
		this.currencyTypeDetail = currencyTypeDetail;
		this.favorite = favorite;
		this.status = status;
		this.symbol = symbol;
	}

	public CountryByCurrencyType() {
		super();
	}

	
	
	
	
	
	
	
}
