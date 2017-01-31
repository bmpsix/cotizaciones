package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_proposal database table.
 * 
 */
@Entity
@Table(name="tbl_log_proposal")
public class LogProposal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false, length=8)
	private String actionUser;

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
	private String idAssesment;

	@Column(name="id_client_contact", nullable=false, length=8)
	private String idClientContact;

	@Column(name="id_colect_method", nullable=false, length=8)
	private String idColectMethod;

	@Column(name="id_country", nullable=false, length=8)
	private String idCountry;

	@Column(name="id_execution_type", nullable=false, length=8)
	private String idExecutionType;

	@Column(name="id_industry_sector", nullable=false, length=8)
	private String idIndustrySector;

	@Column(name="id_proposal", nullable=false, length=8)
	private String idProposal;

	@Column(name="id_proposal_type", nullable=false, length=8)
	private String idProposalType;

	@Column(name="id_status", nullable=false, length=8)
	private String idStatus;

	@Column(name="id_study_category", nullable=false, length=8)
	private String idStudyCategory;

	@Column(name="id_study_type", nullable=false, length=8)
	private String idStudyType;

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

	public String getActionUser() {
		return this.actionUser;
	}

	public void setActionUser(String actionUser) {
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

	public String getIdAssesment() {
		return this.idAssesment;
	}

	public void setIdAssesment(String idAssesment) {
		this.idAssesment = idAssesment;
	}

	public String getIdClientContact() {
		return this.idClientContact;
	}

	public void setIdClientContact(String idClientContact) {
		this.idClientContact = idClientContact;
	}

	public String getIdColectMethod() {
		return this.idColectMethod;
	}

	public void setIdColectMethod(String idColectMethod) {
		this.idColectMethod = idColectMethod;
	}

	public String getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(String idCountry) {
		this.idCountry = idCountry;
	}

	public String getIdExecutionType() {
		return this.idExecutionType;
	}

	public void setIdExecutionType(String idExecutionType) {
		this.idExecutionType = idExecutionType;
	}

	public String getIdIndustrySector() {
		return this.idIndustrySector;
	}

	public void setIdIndustrySector(String idIndustrySector) {
		this.idIndustrySector = idIndustrySector;
	}

	public String getIdProposal() {
		return this.idProposal;
	}

	public void setIdProposal(String idProposal) {
		this.idProposal = idProposal;
	}

	public String getIdProposalType() {
		return this.idProposalType;
	}

	public void setIdProposalType(String idProposalType) {
		this.idProposalType = idProposalType;
	}

	public String getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public String getIdStudyCategory() {
		return this.idStudyCategory;
	}

	public void setIdStudyCategory(String idStudyCategory) {
		this.idStudyCategory = idStudyCategory;
	}

	public String getIdStudyType() {
		return this.idStudyType;
	}

	public void setIdStudyType(String idStudyType) {
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