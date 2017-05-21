package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tbl_trace_response database table.
 * 
 */
@Entity
@Table(name="trace_response")
public class TraceResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_trace_response", unique=true, nullable=false)
	private int idTraceResponse;

	@Column(name="action_user", nullable=false)
	private int actionUser;

	@Column(nullable=false, length=200)
	private String detail;
	
	@Column(nullable=false, length=150)
	private String url;

	@Column(nullable=false, length=50)
	private String ip;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="entry_date", nullable=false)
	private Date entryDate;
	
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}



	

	public int getIdTraceResponse() {
		return idTraceResponse;
	}



	public void setIdTraceResponse(int idTraceResponse) {
		this.idTraceResponse = idTraceResponse;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

	public TraceResponse(int actionUser, String detail, String url, String ip, Date entryDate) {
		super();
		this.actionUser = actionUser;
		this.detail = detail;
		this.url = url;
		this.ip = ip;
		this.entryDate = entryDate;
	}

	public TraceResponse() {
	}

	@Override
	public String toString() {
		return "TraceResponse [idTraceResponse=" + idTraceResponse + ", actionUser=" + actionUser + ", detail=" + detail
				+ ", url=" + url + ", ip=" + ip + ", entryDate=" + entryDate + "]";
	}

	

	
	
	

}