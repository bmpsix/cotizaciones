package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="techniques_by_proposal")
public class TechniquesByProposal implements Serializable {



private static final long serialVersionUID = 1L;
	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_technique_by_proposal", unique=true, nullable=false)
	private int idTechniqueByProposal;


	//bi-directional many-to-one association to Assessment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proposal", nullable=false)
	private Proposal proposal;

	
	//bi-directional many-to-one association to Assessment
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_technique", nullable=false)
	private Technique technique;


	public TechniquesByProposal(Proposal proposal, Technique technique) {
		super();
		this.proposal = proposal;
		this.technique = technique;
	}


	public TechniquesByProposal() {
		super();
	}


	public Proposal getProposal() {
		return proposal;
	}


	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}


	public Technique getTechnique() {
		return technique;
	}


	public void setTechnique(Technique technique) {
		this.technique = technique;
	}

	public int getIdTechniqueByProposal() {
		return idTechniqueByProposal;
	}


	public void setIdTechniqueByProposal(int idTechniqueByProposal) {
		this.idTechniqueByProposal = idTechniqueByProposal;
	}

	@Override
	public String toString() {
		return "TechniqueByProposal [proposal=" + proposal + ", technique=" + technique + "]";
	}

	
	
}
