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

	@Column(name="action_user", length=8)
	private String actionUser;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(nullable=false, length=25)
	private String ip;
	
	@Temporal(TemporalType.DATE)
	@Column(name="entry_date", nullable=false)
	private Date entryDate;
	
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public TraceResponse() {
	}


	

	public int getIdTraceResponse() {
		return idTraceResponse;
	}



	public void setIdTraceResponse(int idTraceResponse) {
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
				+ ", ip=" + ip + ", entrydate"+ entryDate +"]";
	}



	public TraceResponse(String actionUser, String detail, String ip, Date entrydate) {
		super();
	
		this.actionUser = actionUser;
		this.detail = detail;
		this.ip = ip;
		this.entryDate = entrydate;
		
	}

	
	
	

}