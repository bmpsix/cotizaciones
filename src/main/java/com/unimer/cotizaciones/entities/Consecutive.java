package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_consecutives database table.
 * 
 */
@Entity
@Table(name="tbl_consecutives")
public class Consecutive implements Serializable {
	private static final long serialVersionUID = 1L;


	

	@Column(name="detail", nullable=false, length=100)
	private String detail;

	@Column(name="prefix",nullable=false, length=3)
	private String prefix;

	@Column(name="subfix",nullable=false)
	private int subfix;
	
	@Id
	@Column(name="type",nullable=false, unique=true, length=50)
	private String type;



	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getSubfix() {
		return this.subfix;
	}

	public void setSubfix(int subfix) {
		this.subfix = subfix;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Consecutive() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consecutive( String detail, String prefix, int subfix, String type) {
		super();
	
		this.detail = detail;
		this.prefix = prefix;
		this.subfix = subfix;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Consecutive [detail=" + detail + ", prefix=" + prefix + ", subfix=" + subfix + ", type=" + type + "]";
	}

	
}