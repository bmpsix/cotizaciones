package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_study_category database table.
 * 
 */
@Entity
@Table(name="tbl_log_study_category")
public class LogStudyCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false, length=8)
	private String actionUser;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(name="id_study_category", nullable=false, length=8)
	private String idStudyCategory;

	public LogStudyCategory(Date dateRecord, String actionDetail, String actionUser,String idStudyCategory ,String detail ) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.detail = detail;
		this.idStudyCategory = idStudyCategory;	
	
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

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIdStudyCategory() {
		return this.idStudyCategory;
	}

	public void setIdStudyCategory(String idStudyCategory) {
		this.idStudyCategory = idStudyCategory;
	}

}