package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_client_type database table.
 * 
 */
@Entity
@Table(name="log_client_type")
public class LogClientType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;

	@Column(length=100)
	private String detail;

	@Column(name="id_client_type", nullable=false, length=8)
	private int idClientType;

	private byte status;

	public LogClientType() {
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

	public int getIdClientType() {
		return this.idClientType;
	}

	public void setIdClientType(int idClientType) {
		this.idClientType = idClientType;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public LogClientType(Date dateRecord, String actionDetail, int actionUser, String detail, int idClientType,
			byte status) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.detail = detail;
		this.idClientType = idClientType;
		this.status = status;
	}

	
	
	
}