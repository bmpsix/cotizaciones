package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_execution_type database table.
 * 
 */
@Entity
@Table(name="execution_type")
public class ExecutionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_execution_type", unique=true, nullable=false)
	private int idExecutionType;

	@Column(nullable=false, length=100)
	private String detail;

	public int getIdExecutionType() {
		return idExecutionType;
	}

	public void setIdExecutionType(int idExecutionType) {
		this.idExecutionType = idExecutionType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public ExecutionType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExecutionType(int idExecutionType, String detail) {
		super();
		this.idExecutionType = idExecutionType;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ExecutionType [idExecutionType=" + idExecutionType + ", detail=" + detail + "]";
	}
}