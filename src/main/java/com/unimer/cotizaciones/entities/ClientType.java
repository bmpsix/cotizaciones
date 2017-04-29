package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_client_type database table.
 * 
 */
@Entity
@Table(name="client_type")
public class ClientType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_client_type", unique=true, nullable=false)
	private int idClientType;

	@Column(name="detail",length=100)
	private String detail;

	@Column(name="status", nullable=false)
	private byte status;

	public int getIdClientType() {
		return idClientType;
	}

	public void setIdClientType(int idClientType) {
		this.idClientType = idClientType;
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

	public ClientType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientType(int idClientType, String detail, byte status) {
		super();
		this.idClientType = idClientType;
		this.detail = detail;
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClientType [idClientType=" + idClientType + ", detail=" + detail + ", status=" + status+ "]";
	}
}