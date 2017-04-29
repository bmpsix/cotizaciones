package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_technique_by_proposal database table.
 * 
 */
@Entity
@Table(name="log_technique_by_proposal")
public class LogTechniqueByProposal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false, length=8)
	private String actionUser;

	@Column(name="id_proposal", nullable=false, length=8)
	private int idProposal;

	@Column(name="id_technique", nullable=false, length=8)
	private int idTechnique;

	public LogTechniqueByProposal() {
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

	public int getIdProposal() {
		return this.idProposal;
	}

	public void setIdProposal(int idProposal) {
		this.idProposal = idProposal;
	}

	public int getIdTechnique() {
		return this.idTechnique;
	}

	public void setIdTechnique(int idTechnique) {
		this.idTechnique = idTechnique;
	}

}