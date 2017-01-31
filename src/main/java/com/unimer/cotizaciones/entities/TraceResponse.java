package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tbl_trace_response database table.
 * 
 */
@Entity
@Table(name="tbl_trace_response")
public class TraceResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_trace_response", unique=true, nullable=false)
	private Date dateTraceResponse;

	@Column(name="action_user", length=8)
	private String actionUser;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(nullable=false, length=25)
	private String ip;

	public TraceResponse() {
	}

	public Date getDateTraceResponse() {
		return this.dateTraceResponse;
	}

	public void setDateTraceResponse(Date dateTraceResponse) {
		this.dateTraceResponse = dateTraceResponse;
	}

	public String getActionUser() {
		return this.actionUser;
	}

	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}