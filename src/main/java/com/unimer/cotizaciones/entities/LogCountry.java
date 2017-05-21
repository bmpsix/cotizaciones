package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_country database table.
 * 
 */
@Entity
@Table(name="log_country")
public class LogCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false)
	private int actionUser;

	@Column(nullable=false, length=3)
	private String cod;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(name="id_country", nullable=false, length=8)
	private int idCountry;

	

	@Override
	public String toString() {
		return "LogCountry [dateRecord=" + dateRecord + ", actionDetail=" + actionDetail + ", actionUser=" + actionUser
				+ ", cod=" + cod + ", detail=" + detail + ", idCountry=" + idCountry + "]";
	}
	

	public LogCountry() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public LogCountry(Date dateRecord, String actionDetail, int actionUser,
			int idCountry,String detail,String cod) {
		super();
		this.dateRecord = dateRecord;
		this.actionDetail = actionDetail;
		this.actionUser = actionUser;
		this.cod = cod;
		this.detail = detail;
		this.idCountry = idCountry;
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

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

}