package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_currency_type database table.
 * 
 */
@Entity
@Table(name="log_currency_type")
public class LogCurrencyType implements Serializable {
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

	@Column(nullable=false)
	private byte favorite;

	@Column(name="id_curerncy_type", nullable=false, length=8)
	private int idCurrencyType;

	@Column(nullable=false)
	private byte status;

	@Column(nullable=false, length=3)
	private String symbol;

	

	public LogCurrencyType() {
		super();
	}

	public LogCurrencyType(Date dateRecord, String actionDetail, int actionUser, String detail, byte favorite,
			int idCurrencyType, byte status, String symbol) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.detail = detail;
		this.favorite = favorite;
		this.idCurrencyType = idCurrencyType;
		this.status = status;
		this.symbol = symbol;
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

	public byte getFavorite() {
		return this.favorite;
	}

	public void setFavorite(byte favorite) {
		this.favorite = favorite;
	}

	public int getIdCurerncyType() {
		return this.idCurrencyType;
	}

	public void setIdCurerncyType(int idCurerncyType) {
		this.idCurrencyType = idCurerncyType;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}