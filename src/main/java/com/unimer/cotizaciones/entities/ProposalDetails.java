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
@Table(name="proposal_details")
public class ProposalDetails  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proposalDetalis", unique=true, nullable=false)
	private int idProposalDetails;


	@Column(nullable=true, length=100)
	private String detail;
	
	@Column(nullable=true, length=100)
	private String parameters;


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

	
	//bi-directional many-to-one association to CurrencyType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_currency_type", nullable=false)
	private CurrencyType currencyType;
	
	

	public ProposalDetails(int idProposalDetails,String detail, String parameters,Departure departure, double price, byte commissionable, int number, int daysTimes,
			double totalBudget, Proposal proposal, CurrencyType currencyType) {
		super();
		this.idProposalDetails = idProposalDetails;
		this.detail = detail;
		this.parameters = parameters;
		this.departure = departure;
		this.price = price;
		this.commissionable = commissionable;
		this.number = number;
		this.daysTimes = daysTimes;
		this.totalBudget = totalBudget;
		this.proposal = proposal;
		this.currencyType = currencyType;
	}

	public ProposalDetails() {
		super();
		this.departure = new Departure();
		this.proposal = new Proposal();
		this.currencyType = new CurrencyType();
	}
	
	public int getIdProposalDetails() {
		return idProposalDetails;
	}

	public void setIdProposalDetails(int idProposalDetails) {
		this.idProposalDetails = idProposalDetails;
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



	@Override
	public String toString() {
		return "ProposalDetails [idProposalDetails=" + idProposalDetails  + ", detail=" + detail + ", parameters=" + parameters
				+ ", departure=" + departure + ", price=" + price + ", commissionable=" + commissionable + ", number="
				+ number + ", daysTimes=" + daysTimes + ", totalBudget=" + totalBudget + ", proposal=" + proposal
				+ ", currencyType=" + currencyType + "]";
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}
	
}
