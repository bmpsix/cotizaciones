package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_sa_client database table.
 * 
 */
@Entity
@Table(name="sa_client")
public class SaClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sa_client", unique=true, nullable=false)
	private int idSaClient;

	@Column(nullable=false, length=50)
	private String detail;

	@Column(nullable=false)
	private byte status;

	public int getIdSaClient() {
		return idSaClient;
	}

	public void setIdSaClient(int idSaClient) {
		this.idSaClient = idSaClient;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public SaClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaClient(int idSaClient, String detail, byte status) {
		super();
		this.idSaClient = idSaClient;
		this.detail = detail;
		this.status = status;
	}

	@Override
	public String toString() {
		return "SaClient [idSaClient=" + idSaClient + ", detail=" + detail + ", status=" + status + "]";
	}
}