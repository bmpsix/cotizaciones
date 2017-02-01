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

	@Id
	@GeneratedValue
	@Column(name="id_consecutive", unique=true, nullable=false)
	private int idConsecutive;

	@Column(nullable=false, length=100)
	private String detail;

	@Column(nullable=false, length=3)
	private String prefix;

	@Column(nullable=false)
	private int subfix;

	@Column(nullable=false, length=50)
	private String type;

	public int getIdConsecutive() {
		return this.idConsecutive;
	}

	public void setIdConsecutive(int idConsecutive) {
		this.idConsecutive = idConsecutive;
	}

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

	public Consecutive(int idConsecutive, String detail, String prefix, int subfix, String type) {
		super();
		this.idConsecutive = idConsecutive;
		this.detail = detail;
		this.prefix = prefix;
		this.subfix = subfix;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Consecutive [idConsecutive=" + idConsecutive + ", detail=" + detail + ", prefix=" + prefix + ", subfix="
				+ subfix + ", type=" + type + "]";
	}
}