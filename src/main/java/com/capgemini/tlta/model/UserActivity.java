package com.capgemini.tlta.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_activity")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserActivity {
	
	@Id
	@Column(name = "user_activity_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userActivityId;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private RegisterUser login;
	
	@ManyToOne()
	@JoinColumn(name = "activity_id")
	private LearningActivity learningActivity;

	@Column(name = "status")
	private String status = "register";
	
	@Column(name = "certificate")
	private String certificate;

	
}
