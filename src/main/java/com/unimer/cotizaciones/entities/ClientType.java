package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_client_type database table.
 * 
 */
@Entity
@Table(name="tbl_client_type")
public class ClientType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_client_type", unique=true, nullable=false, length=8)
	private String idClientType;

	@Column(length=100)
	private String detail;

	@Column(nullable=false)
	private byte[] status;

	public String getIdClientType() {
		return idClientType;
	}

	public void setIdClientType(String idClientType) {
		this.idClientType = idClientType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public byte[] getStatus() {
		return status;
	}

	public void setStatus(byte[] status) {
		this.status = status;
	}

	public ClientType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientType(String idClientType, String detail, byte[] status) {
		super();
		this.idClientType = idClientType;
		this.detail = detail;
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClientType [idClientType=" + idClientType + ", detail=" + detail + ", status=" + Arrays.toString(status)+ "]";
	}
}