package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tbl_proposal database table.
 * 
 */
@Entity
@Table(name="proposal")
public class Proposal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proposal", unique=true, nullable=false)
	private int idProposal;

	@Column(name="proposal_name", nullable=false)
	private double proposalName;
	
	@Column(name="aporte_fijo", nullable=false)
	private double aporteFijo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date", nullable=false)
	private Date creationDate;

	@Column(name="currency_exchange", nullable=false)
	private double currencyExchange;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date", nullable=false)
	private Date endDate;

	@Column(nullable=false)
	private double factor1;

	@Column(name="imprevisto_comisionable", nullable=false)
	private double imprevistoComisionable;

	@Column(name="imprevisto_no_comisionable", nullable=false)
	private double imprevistoNoComisionable;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="initial_date", nullable=false)
	private Date initialDate;

	@Column(nullable=false, length=100)
	private String observations;

	@Column(name="target", nullable=false, length=100)
	private String targetText;

	//bi-directional many-to-one association to Assessment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_assesment", nullable=false)
	private Assessment assessment;

	//bi-directional many-to-one association to ClientContact
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_client_contact", nullable=false)
	private ClientContact clientContact;

	//bi-directional many-to-one association to CollectMethod
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_colect_method", nullable=false)
	private CollectMethod collectMethod;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_country", nullable=false)
	private Country country;

	//bi-directional many-to-one association to ExecutionType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_execution_type", nullable=false)
	private ExecutionType executionType;

	//bi-directional many-to-one association to IndustrySector
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_industry_sector", nullable=false)
	private IndustrySector industrySector;

	//bi-directional many-to-one association to Operation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_operation", nullable=false)
	private Operation operation;

	//bi-directional many-to-one association to ProposalType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proposal_type", nullable=false)
	private ProposalType proposalType;

	//bi-directional many-to-one association to Status
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_status", nullable=false)
	private Status status;

	//bi-directional many-to-one association to StudyCategory
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_study_category", nullable=false)
	private StudyCategory studyCategory;

	//bi-directional many-to-one association to TblStudyType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_study_type", nullable=false)
	private StudyType studyType;

	//bi-directional many-to-one association to TblTarget
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_target", nullable=false)
	private Target target;

	public int getIdProposal() {
		return idProposal;
	}

	public void setIdProposal(int idProposal) {
		this.idProposal = idProposal;
	}

	public double getAporteFijo() {
		return aporteFijo;
	}

	public void setAporteFijo(double aporteFijo) {
		this.aporteFijo = aporteFijo;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getCurrencyExchange() {
		return currencyExchange;
	}

	public void setCurrencyExchange(double currencyExchange) {
		this.currencyExchange = currencyExchange;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getFactor1() {
		return factor1;
	}

	public void setFactor1(double factor1) {
		this.factor1 = factor1;
	}

	public double getImprevistoComisionable() {
		return imprevistoComisionable;
	}

	public void setImprevistoComisionable(double imprevistoComisionable) {
		this.imprevistoComisionable = imprevistoComisionable;
	}

	public double getImprevistoNoComisionable() {
		return imprevistoNoComisionable;
	}

	public void setImprevistoNoComisionable(double imprevistoNoComisionable) {
		this.imprevistoNoComisionable = imprevistoNoComisionable;
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

	public String getTargetText() {
		return targetText;
	}

	public void setTargetText(String targetText) {
		this.targetText = targetText;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public ClientContact getClientContact() {
		return clientContact;
	}

	public void setClientContact(ClientContact clientContact) {
		this.clientContact = clientContact;
	}

	public CollectMethod getCollectMethod() {
		return collectMethod;
	}

	public void setCollectMethod(CollectMethod collectMethod) {
		this.collectMethod = collectMethod;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public ExecutionType getExecutionType() {
		return executionType;
	}

	public void setExecutionType(ExecutionType executionType) {
		this.executionType = executionType;
	}

	public IndustrySector getIndustrySector() {
		return industrySector;
	}

	public void setIndustrySector(IndustrySector industrySector) {
		this.industrySector = industrySector;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public ProposalType getProposalType() {
		return proposalType;
	}

	public void setProposalType(ProposalType proposalType) {
		this.proposalType = proposalType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public StudyCategory getStudyCategory() {
		return studyCategory;
	}

	public void setStudyCategory(StudyCategory studyCategory) {
		this.studyCategory = studyCategory;
	}

	public StudyType getStudyType() {
		return studyType;
	}

	public void setStudyType(StudyType studyType) {
		this.studyType = studyType;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public Proposal() {
		super();
		this.assessment = new Assessment();
		this.clientContact = new ClientContact();
		this.collectMethod = new CollectMethod();
		this.country = new Country();
		this.executionType = new ExecutionType();
		this.industrySector = new IndustrySector();
		this.operation = new Operation();
		this.proposalType = new ProposalType();
		this.status = new Status();
		this.studyCategory = new StudyCategory();
		this.studyType = new StudyType();
		this.target = new Target();
	}

	public Proposal(int idProposal, double aporteFijo, Date creationDate, double currencyExchange, Date endDate,
			double factor1, double imprevistoComisionable, double imprevistoNoComisionable, Date initialDate,
			String observations, String targetText, Assessment assessment, ClientContact clientContact,
			CollectMethod collectMethod, Country country, ExecutionType executionType, IndustrySector industrySector,
			Operation operation, ProposalType proposalType, Status status, StudyCategory studyCategory,
			StudyType studyType, Target target) {
		super();
		this.idProposal = idProposal;
		this.aporteFijo = aporteFijo;
		this.creationDate = creationDate;
		this.currencyExchange = currencyExchange;
		this.endDate = endDate;
		this.factor1 = factor1;
		this.imprevistoComisionable = imprevistoComisionable;
		this.imprevistoNoComisionable = imprevistoNoComisionable;
		this.initialDate = initialDate;
		this.observations = observations;
		this.targetText = targetText;
		this.assessment = assessment;
		this.clientContact = clientContact;
		this.collectMethod = collectMethod;
		this.country = country;
		this.executionType = executionType;
		this.industrySector = industrySector;
		this.operation = operation;
		this.proposalType = proposalType;
		this.status = status;
		this.studyCategory = studyCategory;
		this.studyType = studyType;
		this.target = target;
	}

	@Override
	public String toString() {
		return "Proposal [idProposal=" + idProposal + ", aporteFijo=" + aporteFijo + ", creationDate=" + creationDate
				+ ", currencyExchange=" + currencyExchange + ", endDate=" + endDate + ", factor1=" + factor1
				+ ", imprevistoComisionable=" + imprevistoComisionable + ", imprevistoNoComisionable="
				+ imprevistoNoComisionable + ", initialDate=" + initialDate + ", observations=" + observations
				+ ", targetText=" + targetText + ", asseament=" + assessment + ", clientContact=" + clientContact
				+ ", collectMethod=" + collectMethod + ", country=" + country + ", executionType=" + executionType
				+ ", industrySector=" + industrySector + ", operation=" + operation + ", proposalType=" + proposalType
				+ ", status=" + status + ", studyCategory=" + studyCategory + ", studyType=" + studyType + ", target="
				+ target + "]";
	}
}