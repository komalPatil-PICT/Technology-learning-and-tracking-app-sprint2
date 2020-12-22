package com.capgemini.tlta.model;
import java.util.Date;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="assesment_activity")
public class Assessment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="assesment_name")
	private String assesment_name;
	
	@Column(name="assesment_type")
	private String assesment_type;
	
	@Temporal(TemporalType.DATE)
	@Column(name="assesment_release_date")
	private Date assesment_release_date;
	
	@Column(name="assesment_time_duration")
	private Double assesment_time_duration;
	
	
	@OneToOne(mappedBy = "assesment",cascade=CascadeType.ALL)
    private LearningActivity learningActivity;
   
	
	public LearningActivity getLearningActivity() {
		return learningActivity;
	}
	public void setLearningActivity(LearningActivity learningActivity) {
		this.learningActivity = learningActivity;
	}
	// Getter and Setter methods
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAssesment_name() {
		return assesment_name;
	}
	public void setAssesment_name(String assesment_name) {
		this.assesment_name = assesment_name;
	}
	public String getAssesment_type() {
		return assesment_type;
	}
	public void setAssesment_type(String assesment_type) {
		this.assesment_type = assesment_type;
	}
	public Date getAssesment_release_date() {
		return assesment_release_date;
	}
	public void setAssesment_release_date(Date assesment_release_date) {
		this.assesment_release_date = assesment_release_date;
	}
	public Double getAssesment_time_duration() {
		return assesment_time_duration;
	}
	public void setAssesment_time_duration(Double assesment_time_duration) {
		this.assesment_time_duration = assesment_time_duration;
	}
	
	// No argument constructor
	public Assessment() {
		
	}
	
	// All argument constructor
	public Assessment(Integer id, String assesment_name, String assesment_type, Date assesment_release_date,
			Double assesment_time_duration) {
		super();
		this.id = id;
		this.assesment_name = assesment_name;
		this.assesment_type = assesment_type;
		this.assesment_release_date = assesment_release_date;
		this.assesment_time_duration = assesment_time_duration;
	}
	
	//toString method
	@Override
	public String toString() {
		return "Assesment [id=" + id + ", assesment_name=" + assesment_name + ", assesment_type=" + assesment_type
				+ ", assesment_release_date=" + assesment_release_date + ", assesment_time_duration="
				+ assesment_time_duration + "]";
	}
	
}
