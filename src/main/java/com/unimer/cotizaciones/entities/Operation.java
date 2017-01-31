package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_operation database table.
 * 
 */
@Entity
@Table(name="tbl_operation")
public class Operation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_operation", unique=true, nullable=false, length=8)
	private String idOperation;

	@Column(nullable=false, length=100)
	private String detail;

	//bi-directional many-to-one association to OperationType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_operation_type", nullable=false)
	private OperationType operationType;

	public String getIdOperation() {
		return idOperation;
	}

	public void setIdOperation(String idOperation) {
		this.idOperation = idOperation;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operation(String idOperation, String detail, OperationType operationType) {
		super();
		this.idOperation = idOperation;
		this.detail = detail;
		this.operationType = operationType;
	}

	@Override
	public String toString() {
		return "Operation [idOperation=" + idOperation + ", detail=" + detail + ", operationType=" + operationType+ "]";
	}
}