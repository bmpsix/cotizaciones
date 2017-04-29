package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_operation_type database table.
 * 
 */
@Entity
@Table(name="operation_type")
public class OperationType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_operation_type", unique=true, nullable=false)
	private int idOperationType;

	@Column(nullable=false, length=100)
	private String detail;

	public int getIdOperationType() {
		return idOperationType;
	}

	public void setIdOperationType(int idOperationType) {
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

	public OperationType(int idOperationType, String detail) {
		super();
		this.idOperationType = (idOperationType);
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "OperationType [idOperationType=" + idOperationType + ", detail=" + detail + "]";
	}
}