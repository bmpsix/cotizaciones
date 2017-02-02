package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_rol database table.
 * 
 */
@Entity
@Table(name="tbl_rol")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_rol", unique=true, nullable=false, length=8)
	private String idRol;

	@Column(name="detail", nullable=false, length=100)
	private String detail;

	@Column(name="status", nullable=false)
	private byte status;

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
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

	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rol(String idRol, String detail, byte status) {
		super();
		this.idRol = idRol;
		this.detail = detail;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", detail=" + detail + ", status=" + status + "]";
	}
}