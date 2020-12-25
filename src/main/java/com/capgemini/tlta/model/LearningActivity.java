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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class LearningActivity.
 */
@Entity
@Table(name = "learning_activity")
@NoArgsConstructor
@Getter 
@Setter
public class LearningActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")	
	private Integer id;

	@Column(name = "activity_name")
	private String activityName;

	@Column(name = "activity_link")
	private String activityLink;

	@Column(name = "activity_level")
	private String activityLevel;

	@Column(name = "activity_time")
	private Double activityTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "activity_realsedate")
	private Date activityReleaseDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "assesment_id", nullable = true)
	private Assessment assesment;

	@JsonIgnore
	@OneToMany(mappedBy = "learningActivity", cascade = CascadeType.ALL)
	private Set<UserActivity> userActivity = new HashSet<UserActivity>();

	public LearningActivity(String activity_name) {
		this.activityName = activity_name;
	}
	
	/**
	 * Gets the serial version uid.
	 *
	 * @return the serial version uid
	 */
	public static long getSerialVersionUid() {
		return serialVersionUID;
	}
}
