package com.unimer.cotizaciones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="assessment_shared")
public class AssessmentShared implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_assessment_shared", unique=true, nullable=false)
	private int idAssessmentShared;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user_shared", nullable=false)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_assessment", nullable=false)
	private Assessment assessment;

	public int getIdAssessmentShared() {
		return idAssessmentShared;
	}

	public void setIdAssessmentShared(int idAssessmentShared) {
		this.idAssessmentShared = idAssessmentShared;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public AssessmentShared(int idAssessmentShared, User user, Assessment assessment) {
		super();
		this.idAssessmentShared = idAssessmentShared;
		this.user = user;
		this.assessment = assessment;
	}

	@Override
	public String toString() {
		return "AssessmentShared [idAssessmentShared=" + idAssessmentShared + ", user=" + user + ", assessment="
				+ assessment + "]";
	}
	
	
	
	
	
	
	
	
}
