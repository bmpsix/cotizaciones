package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_study_type database table.
 * 
 */
@Entity
@Table(name="tbl_study_type")
public class StudyType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_study_type", unique=true, nullable=false, length=8)
	private String idStudyType;

	@Column(nullable=false, length=100)
	private String detail;

	public String getIdStudyType() {
		return idStudyType;
	}

	public void setIdStudyType(String idStudyType) {
		this.idStudyType = idStudyType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public StudyType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudyType(String idStudyType, String detail) {
		super();
		this.idStudyType = idStudyType;
		this.detail = detail;
	}
}