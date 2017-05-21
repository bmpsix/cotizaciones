package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_study_type database table.
 * 
 */
@Entity
@Table(name="log_study_type")
public class LogStudyType implements Serializable {
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

	@Column(name="id_study_type", nullable=false, length=8)
	private int idStudyType;
	
	@Override
	public String toString() {
		return "LogStudyType [dateRecord=" + dateRecord + ", actionDetail=" + actionDetail + ", actionUser="
				+ actionUser + ", detail=" + detail + ", idStudyType=" + idStudyType + "]";
	}
	
	
	public LogStudyType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LogStudyType(Date dateRecord, String actionDetail, int actionUser,String detail ,int idStudyType ) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.detail = detail;
		this.idStudyType = idStudyType;	
	
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

	public int getIdStudyType() {
		return this.idStudyType;
	}

	public void setIdStudyType(int idStudyType) {
		this.idStudyType = idStudyType;
	}

}