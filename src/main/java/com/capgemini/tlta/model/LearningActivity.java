package com.capgemini.tlta.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="learning_activity")
public class LearningActivity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name = "activity_name")
	private String activity_name;
	
	@Column(name="activity_link")
	private String activity_link;
	
	@Column(name="activity_level")
	private String activity_level;
	
	@Column(name="activity_time")
	private Double activity_time;
	
	@Temporal(TemporalType.DATE)
	@Column(name="activity_realsedate")
	private Date activity_realsedate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="assesment_id", nullable = true)
	private Assessment assesment;
	
	@JsonIgnore
	@OneToMany(mappedBy = "learningActivity",cascade = CascadeType.ALL)
	private Set<UserActivity> userActivity = new HashSet<UserActivity>();
	
	public LearningActivity() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getActivity_link() {
		return activity_link;
	}

	public void setActivity_link(String activity_link) {
		this.activity_link = activity_link;
	}

	public String getActivity_level() {
		return activity_level;
	}

	public void setActivity_level(String activity_level) {
		this.activity_level = activity_level;
	}

	public Double getActivity_time() {
		return activity_time;
	}

	public void setActivity_time(Double activity_time) {
		this.activity_time = activity_time;
	}

	public Date getActivity_realsedate() {
		return activity_realsedate;
	}

	public void setActivity_realsedate(Date activity_realsedate) {
		this.activity_realsedate = activity_realsedate;
	}

	public Assessment getAssesment() {
		return assesment;
	}

	public void setAssesment(Assessment assesment) {
		this.assesment = assesment;
	}

	public Set<UserActivity> getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(Set<UserActivity> userActivity) {
		this.userActivity = userActivity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
