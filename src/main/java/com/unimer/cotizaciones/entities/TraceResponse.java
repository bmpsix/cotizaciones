package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_trace_response database table.
 * 
 */
@Entity
@Table(name="tbl_trace_response")
public class TraceResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_trace_response", unique=true, nullable=false, length=8)
	private String idTraceResponse;

	@Column(name="action_user", length=8)
	private String actionUser;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(nullable=false, length=25)
	private String ip;

	public TraceResponse() {
	}

	

	public String getIdTraceResponse() {
		return idTraceResponse;
	}



	public void setIdTraceResponse(String idTraceResponse) {
		this.idTraceResponse = idTraceResponse;
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



	@Override
	public String toString() {
		return "TraceResponse [idTraceResponse=" + idTraceResponse + ", actionUser=" + actionUser + ", detail=" + detail
				+ ", ip=" + ip + "]";
	}



	public TraceResponse(String idTraceResponse, String actionUser, String detail, String ip) {
		super();
		this.idTraceResponse = idTraceResponse;
		this.actionUser = actionUser;
		this.detail = detail;
		this.ip = ip;
	}

	
	
	

}