package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_operation_type database table.
 * 
 */
@Entity
@Table(name="tbl_operation_type")
public class OperationType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_operation_type", unique=true, nullable=false, length=8)
	private String idOperationType;

	@Column(nullable=false, length=100)
	private String detail;

	public String getIdOperationType() {
		return idOperationType;
	}

	public void setIdOperationType(String idOperationType) {
		this.idOperationType = idOperationType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public OperationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperationType(String idOperationType, String detail) {
		super();
		this.idOperationType = idOperationType;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "OperationType [idOperationType=" + idOperationType + ", detail=" + detail + "]";
	}
}