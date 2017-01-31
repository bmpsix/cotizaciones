package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_proposal_type database table.
 * 
 */
@Entity
@Table(name="tbl_proposal_type")
public class ProposalType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_proposal_type", unique=true, nullable=false, length=8)
	private String idProposalType;

	@Column(nullable=false, length=100)
	private String detail;

	public String getIdProposalType() {
		return idProposalType;
	}

	public void setIdProposalType(String idProposalType) {
		this.idProposalType = idProposalType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ProposalType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProposalType(String idProposalType, String detail) {
		super();
		this.idProposalType = idProposalType;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ProposalType [idProposalType=" + idProposalType + ", detail=" + detail + "]";
	}
}