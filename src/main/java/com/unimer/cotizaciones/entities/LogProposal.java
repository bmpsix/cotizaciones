package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_proposal database table.
 * 
 */
@Entity
@Table(name="log_proposal")
public class LogProposal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;

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

	@Column(name="id_assesment", nullable=false, length=8)
	private int idAssesment;

	@Column(name="id_client_contact", nullable=false, length=8)
	private int idClientContact;

	@Column(name="id_colect_method", nullable=false, length=8)
	private int idColectMethod;

	@Column(name="id_country", nullable=false, length=8)
	private int idCountry;

	@Column(name="id_execution_type", nullable=false, length=8)
	private int idExecutionType;

	@Column(name="id_industry_sector", nullable=false, length=8)
	private int idIndustrySector;

	@Column(name="id_proposal", nullable=false, length=8)
	private int idProposal;

	@Column(name="id_proposal_type", nullable=false, length=8)
	private int idProposalType;

	@Column(name="id_status", nullable=false, length=8)
	private int idStatus;

	@Column(name="id_study_category", nullable=false, length=8)
	private int idStudyCategory;

	@Column(name="id_study_type", nullable=false, length=8)
	private int idStudyType;

	@Column(name="imprevisto_comisionable", nullable=false)
	private double imprevistoComisionable;

	@Column(name="imprevisto_no_comisionable", nullable=false)
	private double imprevistoNoComisionable;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="initial_date", nullable=false)
	private Date initialDate;

	@Column(nullable=false, length=100)
	private String observations;

	@Column(nullable=false, length=100)
	private String target;

	public LogProposal() {
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

	public double getAporteFijo() {
		return this.aporteFijo;
	}

	public void setAporteFijo(double aporteFijo) {
		this.aporteFijo = aporteFijo;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getCurrencyExchange() {
		return this.currencyExchange;
	}

	public void setCurrencyExchange(double currencyExchange) {
		this.currencyExchange = currencyExchange;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getFactor1() {
		return this.factor1;
	}

	public void setFactor1(double factor1) {
		this.factor1 = factor1;
	}

	public int getIdAssesment() {
		return this.idAssesment;
	}

	public void setIdAssesment(int idAssesment) {
		this.idAssesment = idAssesment;
	}

	public int getIdClientContact() {
		return this.idClientContact;
	}

	public void setIdClientContact(int idClientContact) {
		this.idClientContact = idClientContact;
	}

	public int getIdColectMethod() {
		return this.idColectMethod;
	}

	public void setIdColectMethod(int idColectMethod) {
		this.idColectMethod = idColectMethod;
	}

	public int getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public int getIdExecutionType() {
		return this.idExecutionType;
	}

	public void setIdExecutionType(int idExecutionType) {
		this.idExecutionType = idExecutionType;
	}

	public int getIdIndustrySector() {
		return this.idIndustrySector;
	}

	public void setIdIndustrySector(int idIndustrySector) {
		this.idIndustrySector = idIndustrySector;
	}

	public int getIdProposal() {
		return this.idProposal;
	}

	public void setIdProposal(int idProposal) {
		this.idProposal = idProposal;
	}

	public int getIdProposalType() {
		return this.idProposalType;
	}

	public void setIdProposalType(int idProposalType) {
		this.idProposalType = idProposalType;
	}

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public int getIdStudyCategory() {
		return this.idStudyCategory;
	}

	public void setIdStudyCategory(int idStudyCategory) {
		this.idStudyCategory = idStudyCategory;
	}

	public int getIdStudyType() {
		return this.idStudyType;
	}

	public void setIdStudyType(int idStudyType) {
		this.idStudyType = idStudyType;
	}

	public double getImprevistoComisionable() {
		return this.imprevistoComisionable;
	}

	public void setImprevistoComisionable(double imprevistoComisionable) {
		this.imprevistoComisionable = imprevistoComisionable;
	}

	public double getImprevistoNoComisionable() {
		return this.imprevistoNoComisionable;
	}

	public void setImprevistoNoComisionable(double imprevistoNoComisionable) {
		this.imprevistoNoComisionable = imprevistoNoComisionable;
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

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}