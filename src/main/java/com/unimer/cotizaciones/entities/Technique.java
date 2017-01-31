package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_technique database table.
 * 
 */
@Entity
@Table(name="tbl_technique")
public class Technique implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_technique", unique=true, nullable=false, length=8)
	private String idTechnique;

	@Column(length=100)
	private String detail;

	public String getIdTechnique() {
		return idTechnique;
	}

	public void setIdTechnique(String idTechnique) {
		this.idTechnique = idTechnique;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Technique() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Technique(String idTechnique, String detail) {
		super();
		this.idTechnique = idTechnique;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Technique [idTechnique=" + idTechnique + ", detail=" + detail + "]";
	}
}