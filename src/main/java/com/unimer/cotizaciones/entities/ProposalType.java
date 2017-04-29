package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_proposal_type database table.
 * 
 */
@Entity
@Table(name="proposal_type")
public class ProposalType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proposal_type", unique=true, nullable=false)
	private int idProposalType;

	@Column(nullable=false, length=100)
	private String detail;

	public int getIdProposalType() {
		return idProposalType;
	}

	public void setIdProposalType(int idProposalType) {
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

	public ProposalType(int idProposalType, String detail) {
		super();
		this.idProposalType = idProposalType;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ProposalType [idProposalType=" + idProposalType + ", detail=" + detail + "]";
	}
}