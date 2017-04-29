package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_study_type database table.
 * 
 */
@Entity
@Table(name="study_type")
public class StudyType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_study_type", unique=true, nullable=false)
	private int idStudyType;

	@Column(nullable=false, length=100)
	private String detail;

	public int getIdStudyType() {
		return idStudyType;
	}

	public void setIdStudyType(int idStudyType) {
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

	public StudyType(int idStudyType, String detail) {
		super();
		this.idStudyType = idStudyType;
		this.detail = detail;
	}
	
	@Override
	public String toString() {
		return "StudyType [idStudyType=" + idStudyType + ", detail=" + detail + "]";
	}
}