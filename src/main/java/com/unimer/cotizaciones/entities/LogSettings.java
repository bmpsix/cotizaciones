package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="log_settings")
public class LogSettings implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;

	@Column(name="id_settings", unique=false, nullable=false)
	private int idSettings;
	
	@Column(name="aporte_fijo", nullable=false)
	private double aporteFijo;
	
	@Column(nullable=false)
	private double factor1;
	
	@Column(nullable=false)
	private double factor2;
	
	@Column(name="imprevisto", nullable=true)
	private double imprevisto;

	@Column(name="id_country", nullable=false)
	private int idCountry;
	
	@Column(name="id_currency_type", nullable=false)
	private int idCurrencyType;

	public LogSettings(Date dateRecord, String actionDetail, int actionUser, int idSettings, double aporteFijo,
			double factor1, double factor2, double imprevisto, int idCountry, int idCurrencyType) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.idSettings = idSettings;
		this.aporteFijo = aporteFijo;
		this.factor1 = factor1;
		this.factor2 = factor2;
		this.imprevisto = imprevisto;
		this.idCountry = idCountry;
		this.idCurrencyType = idCurrencyType;
	}

	public LogSettings() {
		super();
	}

	
	@Override
	public String toString() {
		return "LogSettings [dateRecord=" + dateRecord + ", actionDetail=" + actionDetail + ", actionUser=" + actionUser
				+ ", idSettings=" + idSettings + ", aporteFijo=" + aporteFijo + ", factor1=" + factor1 + ", factor2="
				+ factor2 + ", imprevisto=" + imprevisto + ", idCountry=" + idCountry + ", idCurrencyType="
				+ idCurrencyType + "]";
	}

	public Date getDateRecord() {
		return dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	public String getActionDetail() {
		return actionDetail;
	}

	public void setActionDetail(String actionDetail) {
		this.actionDetail = actionDetail;
	}

	public int getActionUser() {
		return actionUser;
	}

	public void setActionUser(int actionUser) {
		this.actionUser = actionUser;
	}

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

	
	

}
