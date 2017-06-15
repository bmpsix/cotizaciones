package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the tbl_log_departure database table.
 * 
 */

@Entity
@Table(name="log_departure")
public class LogDeparture implements Serializable {
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getIdCurrencyType() {
		return idCurrencyType;
	}

	public void setIdCurrencyType(int idCurrencyType) {
		this.idCurrencyType = idCurrencyType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(name="id_departure", nullable=false, length=8)
	private int idDeparture;

	@Column(name="status", nullable=false)
	private byte status;

	@JoinColumn(name="id_currency_type", nullable=false)
	private int idCurrencyType;
	
	@Column(name="price", nullable=true)
	private double price;
	
	
	
	public LogDeparture() {
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

	public int getActionUser() {
		return this.actionUser;
	}

	public void setActionUser(int actionUser) {
		this.actionUser = actionUser;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getIdDeparture() {
		return this.idDeparture;
	}

	public void setIdDeparture(int idDeparture) {
		this.idDeparture = idDeparture;
	}

	
	public LogDeparture(Date dateRecord, String actionDetail, int actionUser, String detail, int idDeparture,
			byte status, int idCurrencyType, double price) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.detail = detail;
		this.idDeparture = idDeparture;
		this.status = status;
		this.idCurrencyType = idCurrencyType;
		this.price = price;
	}

	
	
}
