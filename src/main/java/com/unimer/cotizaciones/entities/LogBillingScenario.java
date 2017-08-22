package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="log_billing_scenario")
public class LogBillingScenario implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_log_billing_scenario", unique=true, nullable=false)
	private int idLogBillingScenario;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;
	
	@Column(name="id_silling_scenario", nullable=false)
	private int idBillingScenario;

	@Column(name="id_proposal", nullable=false)
	private int proposal;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="initial_date", nullable=false)
	private Date initialDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="registration_date", nullable=false)
	private Date registrationDate;
	
	@Column(name="id_user", nullable=false)
	private int user;
	
	
	@Column(name="id_country", nullable=false)
	private int country;
	
	@Column(name="tranference_value",nullable=false)
	private double tranferenceValue;
	
	@Column(name="remittance",nullable=false)
	private double remittance;
	
	@Column(name="iva",nullable=false)
	private double iva;
	
	@Column(name="total_amount",nullable=false)
	private double totalAmount;

	
	@Column(name="tranference_value_modified",nullable=true)
	private double tranferenceValueModified;
	
	@Column(name="remittance_modified",nullable=true)
	private double remittanceModified;
	
	@Column(name="iva_modified",nullable=true)
	private double ivaModified;
	
	@Column(name="total_amount_modified",nullable=true)
	private double totalAmountModified;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modification_date", nullable=true)
	private Date lastModificationDate;
	
	@Column(name="method_state",nullable=false)
	private byte methodState;
	
	@Column(name="id_sa_client", nullable=false)
	private int saClient;
	
	@Column(name="id_client_contact", nullable=false)
	private int clientContact;

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

	public int getIdBillingScenario() {
		return idBillingScenario;
	}

	public void setIdBillingScenario(int idBillingScenario) {
		this.idBillingScenario = idBillingScenario;
	}

	public int getProposal() {
		return proposal;
	}

	public void setProposal(int proposal) {
		this.proposal = proposal;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public double getTranferenceValue() {
		return tranferenceValue;
	}

	public void setTranferenceValue(double tranferenceValue) {
		this.tranferenceValue = tranferenceValue;
	}

	public double getRemittance() {
		return remittance;
	}

	public void setRemittance(double remittance) {
		this.remittance = remittance;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTranferenceValueModified() {
		return tranferenceValueModified;
	}

	public void setTranferenceValueModified(double tranferenceValueModified) {
		this.tranferenceValueModified = tranferenceValueModified;
	}

	public double getRemittanceModified() {
		return remittanceModified;
	}

	public void setRemittanceModified(double remittanceModified) {
		this.remittanceModified = remittanceModified;
	}

	public double getIvaModified() {
		return ivaModified;
	}

	public void setIvaModified(double ivaModified) {
		this.ivaModified = ivaModified;
	}

	public double getTotalAmountModified() {
		return totalAmountModified;
	}

	public void setTotalAmountModified(double totalAmountModified) {
		this.totalAmountModified = totalAmountModified;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public byte getMethodState() {
		return methodState;
	}

	public void setMethodState(byte methodState) {
		this.methodState = methodState;
	}

	public int getSaClient() {
		return saClient;
	}

	public void setSaClient(int saClient) {
		this.saClient = saClient;
	}

	public int getClientContact() {
		return clientContact;
	}

	public void setClientContact(int clientContact) {
		this.clientContact = clientContact;
	}

	

	public int getIdLogBillingScenario() {
		return idLogBillingScenario;
	}

	public void setIdLogBillingScenario(int idLogBillingScenario) {
		this.idLogBillingScenario = idLogBillingScenario;
	}

	

	public LogBillingScenario(Date dateRecord, String actionDetail, int actionUser,
			int idBillingScenario, int proposal, Date initialDate, Date registrationDate, int user, int country,
			double tranferenceValue, double remittance, double iva, double totalAmount, double tranferenceValueModified,
			double remittanceModified, double ivaModified, double totalAmountModified, Date lastModificationDate,
			byte methodState, int saClient, int clientContact) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.idBillingScenario = idBillingScenario;
		this.proposal = proposal;
		this.initialDate = initialDate;
		this.registrationDate = registrationDate;
		this.user = user;
		this.country = country;
		this.tranferenceValue = tranferenceValue;
		this.remittance = remittance;
		this.iva = iva;
		this.totalAmount = totalAmount;
		this.tranferenceValueModified = tranferenceValueModified;
		this.remittanceModified = remittanceModified;
		this.ivaModified = ivaModified;
		this.totalAmountModified = totalAmountModified;
		this.lastModificationDate = lastModificationDate;
		this.methodState = methodState;
		this.saClient = saClient;
		this.clientContact = clientContact;
	}

	public LogBillingScenario() {
		super();
	}

	@Override
	public String toString() {
		return "LogBillingScenario [idLogBillingScenario=" + idLogBillingScenario + ", dateRecord=" + dateRecord
				+ ", actionDetail=" + actionDetail + ", actionUser=" + actionUser + ", idBillingScenario="
				+ idBillingScenario + ", proposal=" + proposal + ", initialDate=" + initialDate + ", registrationDate="
				+ registrationDate + ", user=" + user + ", country=" + country + ", tranferenceValue="
				+ tranferenceValue + ", remittance=" + remittance + ", iva=" + iva + ", totalAmount=" + totalAmount
				+ ", tranferenceValueModified=" + tranferenceValueModified + ", remittanceModified="
				+ remittanceModified + ", ivaModified=" + ivaModified + ", totalAmountModified=" + totalAmountModified
				+ ", lastModificationDate=" + lastModificationDate + ", methodState=" + methodState + ", saClient="
				+ saClient + ", clientContact=" + clientContact + "]";
	}
	
	
	
	
	
	
	
	
}
