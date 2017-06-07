package com.unimer.cotizaciones.entities;

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
@Table(name="proposal_details")
public class ProposalDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proposalDetalis", unique=true, nullable=false)
	private int idProposalDetails;
	

	@Column(name="aporte_fijo", nullable=false)
	private double aporteFijo;
	
	@Column(nullable=false)
	private double factor;


	@Column(name="imprevisto", nullable=true)
	private double imprevisto;

	//bi-directional many-to-one association to ExecutionType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_departure", nullable=false)
	private Departure departure;
	
	@Column(name="price", nullable=false)
	private double price;
	
	
	@Column(name="commissionable", nullable=false)
	private byte commissionable;
	
	@Column(name="number", nullable=false)
	private int number;
	
	
	@Column(name="days_times", nullable=false)
	private int daysTimes;
	
	@Column(name="total_budget", nullable=false)
	private int totalBudget;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proposal", nullable=false)
	private Proposal proposal;

	public ProposalDetails(int idProposalDetails, double aporteFijo, double factor, double imprevisto,
			Departure departure, double price, byte commissionable, int number, int daysTimes, int totalBudget,
			Proposal proposal) {
		super();
		this.idProposalDetails = idProposalDetails;
		this.aporteFijo = aporteFijo;
		this.factor = factor;
		this.imprevisto = imprevisto;
		this.departure = departure;
		this.price = price;
		this.commissionable = commissionable;
		this.number = number;
		this.daysTimes = daysTimes;
		this.totalBudget = totalBudget;
		this.proposal = proposal;
	}

	public ProposalDetails() {
		super();
		this.departure = new Departure();
		this.proposal = new Proposal();
	}
	
	
	
	
}
