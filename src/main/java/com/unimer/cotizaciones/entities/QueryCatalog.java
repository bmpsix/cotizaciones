package com.unimer.cotizaciones.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_query_catalog")
public class QueryCatalog {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_query_catalog", unique=true, nullable=false)
	private int id_query_catalog;
	
	@Column(name="detail",nullable=false, length=100)
	private String detail;
	
	@Column(name="row_count", nullable=false)
	private long row_count;
	
	@Column(name="code", nullable=false, length=8)
	private String code;
	
	@Column(name="country_id", nullable=false, length=3)
	private int country_id;

	@Override
	public String toString() {
		return "QueryCatalog [id_query_catalog=" + id_query_catalog + ", detail=" + detail + ", row_count=" + row_count
				+ ", code=" + code + ", country_id=" + country_id + "]";
	}
	
	

	public QueryCatalog() {
		super();
		
	}



	public QueryCatalog(int id, String detail, long row_count, String code, int country_id) {
		super();
		this.id_query_catalog = id;
		this.detail = detail;
		this.row_count = row_count;
		this.code = code;
		this.country_id = country_id;
	}

	public int getId_query_catalog() {
		return id_query_catalog;
	}

	public void setId_query_catalog(int id_query_catalog) {
		this.id_query_catalog = id_query_catalog;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public long getRow_count() {
		return row_count;
	}

	public void setRow_count(long row_count) {
		this.row_count = row_count;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	
	
	
	
	
	
}
