package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_study_category database table.
 * 
 */
@Entity
@Table(name="tbl_study_category")
public class StudyCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_study_category", unique=true, nullable=false, length=8)
	private String idStudyCategory;

	@Column(name="detail", nullable=false, length=100)
	private String detail;

	public String getIdStudyCategory() {
		return idStudyCategory;
	}

	public void setIdStudyCategory(String idStudyCategory) {
		this.idStudyCategory = idStudyCategory;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public StudyCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudyCategory(String idStudyCategory, String detail) {
		super();
		this.idStudyCategory = idStudyCategory;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "StudyCategory [idStudyCategory=" + idStudyCategory + ", detail=" + detail + "]";
	}
}