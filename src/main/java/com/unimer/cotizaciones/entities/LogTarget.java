package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_target database table.
 * 
 */
@Entity
@Table(name="log_target")
public class LogTarget implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date", nullable=false)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date", nullable=false)
	private Date endDate;

	@Column(name="id_target", nullable=false, length=8)
	private int idTarget;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="initial_date", nullable=false)
	private Date initialDate;

	@Column(nullable=false, length=100)
	private String observations;

	public LogTarget() {
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

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getIdTarget() {
		return this.idTarget;
	}

	public void setIdTarget(int idTarget) {
		this.idTarget = idTarget;
	}

	public Date getInitialDate() {
		return this.initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	@Override
	public String toString() {
		return "LogTarget [dateRecord=" + dateRecord + ", actionDetail=" + actionDetail + ", actionUser=" + actionUser
				+ ", creationDate=" + creationDate + ", endDate=" + endDate + ", idTarget=" + idTarget
				+ ", initialDate=" + initialDate + ", observations=" + observations + "]";
	}

	public LogTarget(Date dateRecord, String actionDetail, int actionUser, Date creationDate, Date endDate,
			int idTarget, Date initialDate, String observations) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.creationDate = creationDate;
		this.endDate = endDate;
		this.idTarget = idTarget;
		this.initialDate = initialDate;
		this.observations = observations;
	}
	

}