package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_study_category database table.
 * 
 */
@Entity
@Table(name="study_category")
public class StudyCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_study_category", unique=true, nullable=false)
	private int idStudyCategory;

	@Column(name="detail", nullable=false, length=100)
	private String detail;

	public int getIdStudyCategory() {
		return idStudyCategory;
	}

	public void setIdStudyCategory(int idStudyCategory) {
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

	public StudyCategory(int idStudyCategory, String detail) {
		super();
		this.idStudyCategory = idStudyCategory;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "StudyCategory [idStudyCategory=" + idStudyCategory + ", detail=" + detail + "]";
	}
}