package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_industry_sector database table.
 * 
 */
@Entity
@Table(name="industry_sector")
public class IndustrySector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_industry_sector", unique=true, nullable=false)
	private int idIndustrySector;

	@Column(nullable=false, length=100)
	private String detail;

	public int getIdIndustrySector() {
		return idIndustrySector;
	}

	public void setIdIndustrySector(int idIndustrySector) {
		this.idIndustrySector = idIndustrySector;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public IndustrySector() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IndustrySector(int idIndustrySector, String detail) {
		super();
		this.idIndustrySector = idIndustrySector;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "IndustrySector [idIndustrySector=" + idIndustrySector + ", detail=" + detail + "]";
	}
}