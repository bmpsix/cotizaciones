package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_country_by_currency_type database table.
 * 
 */
@Entity
@Table(name="log_country_by_currency_type")
public class LogCountryByCurrencyType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false, length=8)
	private String actionUser;

	@Column(name="id_country", nullable=false, length=8)
	private int idCountry;

	@Column(name="id_currency_type", nullable=false, length=8)
	private int idCurrencyType;

	public LogCountryByCurrencyType() {
	}

	public Date getDateRecord() {
		return this.dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	public String getActionDetail() {
		return this.actionDetail;
	}

	public void setActionDetail(String actionDetail) {
		this.actionDetail = actionDetail;
	}

	public String getActionUser() {
		return this.actionUser;
	}

	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}

	public int getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public int getIdCurrencyType() {
		return this.idCurrencyType;
	}

	public void setIdCurrencyType(int idCurrencyType) {
		this.idCurrencyType = idCurrencyType;
	}

	@Override
	public String toString() {
		return "LogCountryByCurrencyType [dateRecord=" + dateRecord + ", actionDetail=" + actionDetail + ", actionUser="
				+ actionUser + ", idCountry=" + idCountry + ", idCurrencyType=" + idCurrencyType + "]";
	}
	
	

}