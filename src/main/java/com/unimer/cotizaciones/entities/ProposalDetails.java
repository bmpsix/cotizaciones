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

	@Column(nullable=true, length=100)
	private String detail;
	
	@Column(nullable=true, length=100)
	private String parameters;

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
	private double totalBudget;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proposal", nullable=false)
	private Proposal proposal;

	
	
	
	

	public ProposalDetails(int idProposalDetails, double aporteFijo, double factor, String detail, String parameters,
			double imprevisto, Departure departure, double price, byte commissionable, int number, int daysTimes,
			double totalBudget, Proposal proposal) {
		super();
		this.idProposalDetails = idProposalDetails;
		this.aporteFijo = aporteFijo;
		this.factor = factor;
		this.detail = detail;
		this.parameters = parameters;
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
	
	public int getIdProposalDetails() {
		return idProposalDetails;
	}

	public void setIdProposalDetails(int idProposalDetails) {
		this.idProposalDetails = idProposalDetails;
	}

	public double getAporteFijo() {
		return aporteFijo;
	}

	public void setAporteFijo(double aporteFijo) {
		this.aporteFijo = aporteFijo;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public double getImprevisto() {
		return imprevisto;
	}

	public void setImprevisto(double imprevisto) {
		this.imprevisto = imprevisto;
	}

	public Departure getDeparture() {
		return departure;
	}

	public void setDeparture(Departure departure) {
		this.departure = departure;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte getCommissionable() {
		return commissionable;
	}

	public void setCommissionable(byte commissionable) {
		this.commissionable = commissionable;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getDaysTimes() {
		return daysTimes;
	}

	public void setDaysTimes(int daysTimes) {
		this.daysTimes = daysTimes;
	}

	public double getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(double totalBudget) {
		this.totalBudget = totalBudget;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	
	
}
