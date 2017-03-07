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
 * The persistent class for the tbl_target database table.
 * 
 */
@Entity
@Table(name="tbl_target")
public class Target implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_target", unique=true, nullable=false, length=8)
	private String idTarget;

	@Temporal(TemporalType.TIMESTAMP)

	@Column(name="creation_date", nullable=false)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)

	@Column(name="end_date", nullable=false)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)

	@Column(name="initial_date", nullable=false)
	private Date initialDate;

	@Column(nullable=false, length=100)
	private String observations;

	public String getIdTarget() {
		return idTarget;
	}

	public void setIdTarget(String idTarget) {
		this.idTarget = idTarget;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Target() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Target(String idTarget, Date creationDate, Date endDate, Date initialDate, String observations) {
		super();
		this.idTarget = idTarget;
		this.creationDate = creationDate;
		this.endDate = endDate;
		this.initialDate = initialDate;
		this.observations = observations;
	}

	@Override
	public String toString() {
		return "Target [idTarget=" + idTarget + ", creationDate=" + creationDate + ", endDate=" + endDate
				+ ", initialDate=" + initialDate + ", observations=" + observations + "]";
	}
}