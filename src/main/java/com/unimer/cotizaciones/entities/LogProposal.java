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
	
	
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;


	@Column(name="proposal_name", nullable=false)
	private String proposalName;
	
	@Column(name="projectType", nullable=false)
	private String projectType;
	
	@Column(name="tracker", nullable=false)
	private String tracker;
	
	@Column(name="aporte_fijo", nullable=false)
	private double aporteFijo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date", nullable=false)
	private Date creationDate;

	@Column(name="currency_exchange", nullable=false)
	private double currencyExchange;
	
	
	
	@Column(name="id_currencyType", nullable=false)
	private int idCurrencyType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date", nullable=false)
	private Date endDate;

	@Column(nullable=false)
	private double factor1;
	
	@Column(nullable=false)
	private double factor2;
	
	
	@Column(name="imprevisto", nullable=true)
	private double imprevisto;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="initial_date", nullable=false)
	private Date initialDate;

	@Column(nullable=false, length=100)
	private String observations;

	@Column(name="target", nullable=false, length=100)
	private String targetText;

	
	@Column(name="id_assesment", nullable=false)
	private int idssessment;

	
	@Column(name="id_client_contact", nullable=false)
	private int idClientContact;

	
	@Column(name="id_colect_method", nullable=false)
	private int idCollectMethod;


	@Column(name="id_country", nullable=false)
	private int idCountry;

	
	@Column(name="id_execution_type", nullable=false)
	private int idExecutionType;


	@Column(name="id_industry_sector", nullable=false)
	private int idIndustrySector;


	@Column(name="id_operation", nullable=false)
	private int idOperation;

	
	@Column(name="id_proposal_type", nullable=false)
	private int idProposalType;

	
	@Column(name="id_status", nullable=false)
	private int idStatus;

	
	@Column(name="id_study_category", nullable=false)
	private int idStudyCategory;

	
	@Column(name="id_study_type", nullable=false)
	private int idStudyType;


	
	@Column(name="id_technique", nullable=false)
	private int idTechnique;


	@Column(name="id_user", nullable=false)
	private int idUser;

	
	
	

	public Date getDateRecord() {
		return dateRecord;
	}


	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}


	public String getActionDetail() {
		return actionDetail;
	}


	public void setActionDetail(String actionDetail) {
		this.actionDetail = actionDetail;
	}


	public int getActionUser() {
		return actionUser;
	}


	public void setActionUser(int actionUser) {
		this.actionUser = actionUser;
	}


	public String getProposalName() {
		return proposalName;
	}


	public void setProposalName(String proposalName) {
		this.proposalName = proposalName;
	}


	public String getProjectType() {
		return projectType;
	}


	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}


	public String getTracker() {
		return tracker;
	}


	public void setTracker(String tracker) {
		this.tracker = tracker;
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


	public int getIdCurrencyType() {
		return idCurrencyType;
	}


	public void setIdCurrencyType(int idCurrencyType) {
		this.idCurrencyType = idCurrencyType;
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


	public double getFactor2() {
		return factor2;
	}


	public void setFactor2(double factor2) {
		this.factor2 = factor2;
	}


	public double getImprevisto() {
		return imprevisto;
	}


	public void setImprevisto(double imprevisto) {
		this.imprevisto = imprevisto;
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


	public int getIdssessment() {
		return idssessment;
	}


	public void setIdssessment(int idssessment) {
		this.idssessment = idssessment;
	}


	public int getIdClientContact() {
		return idClientContact;
	}


	public void setIdClientContact(int idClientContact) {
		this.idClientContact = idClientContact;
	}


	public int getIdCollectMethod() {
		return idCollectMethod;
	}


	public void setIdCollectMethod(int idCollectMethod) {
		this.idCollectMethod = idCollectMethod;
	}


	public int getIdCountry() {
		return idCountry;
	}


	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}


	public int getIdExecutionType() {
		return idExecutionType;
	}


	public void setIdExecutionType(int idExecutionType) {
		this.idExecutionType = idExecutionType;
	}


	public int getIdIndustrySector() {
		return idIndustrySector;
	}


	public void setIdIndustrySector(int idIndustrySector) {
		this.idIndustrySector = idIndustrySector;
	}


	public int getIdOperation() {
		return idOperation;
	}


	public void setIdOperation(int idOperation) {
		this.idOperation = idOperation;
	}


	public int getIdProposalType() {
		return idProposalType;
	}


	public void setIdProposalType(int idProposalType) {
		this.idProposalType = idProposalType;
	}


	public int getIdStatus() {
		return idStatus;
	}


	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}


	public int getIdStudyCategory() {
		return idStudyCategory;
	}


	public void setIdStudyCategory(int idStudyCategory) {
		this.idStudyCategory = idStudyCategory;
	}


	public int getIdStudyType() {
		return idStudyType;
	}


	public void setIdStudyType(int idStudyType) {
		this.idStudyType = idStudyType;
	}


	public int getIdTechnique() {
		return idTechnique;
	}


	public void setIdTechnique(int idTechnique) {
		this.idTechnique = idTechnique;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	private static final long serialVersionUID = 1L;

	

	public LogProposal(Date dateRecord, String actionDetail, int actionUser, String proposalName, String projectType,
			String tracker, double aporteFijo, Date creationDate, double currencyExchange, int idCurrencyType,
			Date endDate, double factor1, double factor2, double imprevisto, Date initialDate, String observations,
			String targetText, int idssessment, int idClientContact, int idCollectMethod, int idCountry,
			int idExecutionType, int idIndustrySector, int idOperation, int idProposalType, int idStatus,
			int idStudyCategory, int idStudyType, int idTechnique, int idUser) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.proposalName = proposalName;
		this.projectType = projectType;
		this.tracker = tracker;
		this.aporteFijo = aporteFijo;
		this.creationDate = creationDate;
		this.currencyExchange = currencyExchange;
		this.idCurrencyType = idCurrencyType;
		this.endDate = endDate;
		this.factor1 = factor1;
		this.factor2 = factor2;
		this.imprevisto = imprevisto;
		this.initialDate = initialDate;
		this.observations = observations;
		this.targetText = targetText;
		this.idssessment = idssessment;
		this.idClientContact = idClientContact;
		this.idCollectMethod = idCollectMethod;
		this.idCountry = idCountry;
		this.idExecutionType = idExecutionType;
		this.idIndustrySector = idIndustrySector;
		this.idOperation = idOperation;
		this.idProposalType = idProposalType;
		this.idStatus = idStatus;
		this.idStudyCategory = idStudyCategory;
		this.idStudyType = idStudyType;
		this.idTechnique = idTechnique;
		this.idUser = idUser;
	}


	public LogProposal() {
		super();
	}
	
	@Override
	public String toString() {
		return "LogProposal [dateRecord=" + dateRecord + ", actionDetail=" + actionDetail + ", actionUser=" + actionUser
				+ ", proposalName=" + proposalName + ", projectType=" + projectType + ", tracker=" + tracker
				+ ", aporteFijo=" + aporteFijo + ", creationDate=" + creationDate + ", currencyExchange="
				+ currencyExchange + ", idCurrencyType=" + idCurrencyType + ", endDate=" + endDate + ", factor1="
				+ factor1 + ", factor2=" + factor2 + ", imprevisto=" + imprevisto + ", initialDate=" + initialDate
				+ ", observations=" + observations + ", targetText=" + targetText + ", idssessment=" + idssessment
				+ ", idClientContact=" + idClientContact + ", idCollectMethod=" + idCollectMethod + ", idCountry="
				+ idCountry + ", idExecutionType=" + idExecutionType + ", idIndustrySector=" + idIndustrySector
				+ ", idOperation=" + idOperation + ", idProposalType=" + idProposalType + ", idStatus=" + idStatus
				+ ", idStudyCategory=" + idStudyCategory + ", idStudyType=" + idStudyType + ", idTechnique="
				+ idTechnique + ", idUser=" + idUser + "]";
	}

	
	
	
}