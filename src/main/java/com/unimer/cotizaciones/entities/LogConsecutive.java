package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_log_consecutives database table.
 * 
 */
@Entity
@Table(name="tbl_log_consecutives")
public class LogConsecutive implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_record", unique=true, nullable=false)
	private Date dateRecord;

	@Column(name="action_detail", nullable=false, length=100)
	private String actionDetail;

	@Column(name="action_user", nullable=false, length=8)
	private String actionUser;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(name="id_consecutive", nullable=false)
	private int idConsecutive;

	@Column(nullable=false, length=3)
	private String prefix;

	@Column(nullable=false)
	private int subfix;

	@Column(nullable=false, length=50)
	private String type;

	public LogConsecutive() {
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

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getIdConsecutive() {
		return this.idConsecutive;
	}

	public void setIdConsecutive(int idConsecutive) {
		this.idConsecutive = idConsecutive;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getSubfix() {
		return this.subfix;
	}

	public void setSubfix(int subfix) {
		this.subfix = subfix;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}