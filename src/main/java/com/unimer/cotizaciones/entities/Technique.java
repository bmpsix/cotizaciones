package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_technique database table.
 * 
 */
@Entity
@Table(name="technique")
public class Technique implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_technique", unique=true, nullable=false)
	private int idTechnique;

	@Column(length=100)
	private String detail;

	
	public int getIdTechnique() {
		return idTechnique;
	}

	public void setIdTechnique(int idTechnique) {
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

	public Technique(int idTechnique, String detail) {
		super();
		this.idTechnique = idTechnique;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Technique [idTechnique=" + idTechnique + ", detail=" + detail + "]";
	}
}