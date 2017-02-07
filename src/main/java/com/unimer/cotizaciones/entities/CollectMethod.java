package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_collect_method database table.
 * 
 */
@Entity
@Table(name="tbl_collect_method")
public class CollectMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_collect_method", unique=true, nullable=false, length=8)
	private String idCollectMethod;

	@Column(nullable=false, length=100)
	private String detail;

	public String getIdCollectMethod() {
		return idCollectMethod;
	}

	public void setIdCollectMethod(String idCollectMethod) {
		this.idCollectMethod = idCollectMethod;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public CollectMethod() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollectMethod(String idCollectMethod, String detail) {
		super();
		this.idCollectMethod = idCollectMethod;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "CollectMethod [idCollectMethod=" + idCollectMethod + ", detail=" + detail + "]";
	}
}