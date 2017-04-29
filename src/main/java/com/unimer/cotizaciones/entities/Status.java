package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_status database table.
 * 
 */
@Entity
@Table(name="status")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_status", unique=true, nullable=false)
	private int idStatus;

	@Column(nullable=false, length=100)
	private String detail;

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Status(int idStatus, String detail) {
		super();
		this.idStatus = idStatus;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Status [idStatus=" + idStatus + ", detail=" + detail + "]";
	}
}