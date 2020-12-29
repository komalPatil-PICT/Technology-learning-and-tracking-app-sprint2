package com.capgemini.tlta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.tlta.sevice.UserActivityDO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class UserActivity.
 */

@Entity
@Table(name = "user_activity")
@NoArgsConstructor
@Getter 
@Setter
public class UserActivity {
	
	@Id
	@Column(name = "user_activity_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer userActivityId;	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private RegisterUser registerUser;
	
	@ManyToOne()
	@JoinColumn(name = "activity_id")
	private LearningActivity learningActivity;

	@Column(name = "status")
	private String status = "register";
	
	@Column(name = "certificate")
	private String certificate;
	
	@JsonIgnore
	@Column
	@Lob
	private byte[] file;
	
	
	public UserActivity(UserActivityDO userDo) {
		status = userDo.getStatus();
		certificate = userDo.getCertificate();
	}
	public UserActivity(String status) {
		this.status=status;
	}
	public UserActivity(RegisterUser registerUser, LearningActivity learningActivity, String status, String certificate,
			byte[] file) {
		super();
		this.registerUser = registerUser;
		this.learningActivity = learningActivity;
		this.status = status;
		this.certificate = certificate;
		this.file = file;
	}
	public UserActivity(RegisterUser registerUser, LearningActivity learningActivity, String status,
			String certificate) {
		super();
		this.registerUser = registerUser;
		this.learningActivity = learningActivity;
		this.status = status;
		this.certificate = certificate;
	}
	
}
