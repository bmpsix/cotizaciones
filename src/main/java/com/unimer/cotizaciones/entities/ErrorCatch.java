package com.unimer.cotizaciones.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_error_catch database table.
 * 
 */
@Entity
@Table(name="tbl_error_catch")
public class ErrorCatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_error_catch", unique=true, nullable=false)
	private Date dateErrorCatch;

	@Column(nullable=false, length=500)
	private String detail;

	@Column(name="id_user", length=8)
	private String idUser;

	public ErrorCatch() {
	}

	public Date getDateErrorCatch() {
		return this.dateErrorCatch;
	}

	public void setDateErrorCatch(Date dateErrorCatch) {
		this.dateErrorCatch = dateErrorCatch;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

}