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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="learning_activity")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
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
	
	@ToString.Exclude
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="assesment_id")
	@JsonIgnore
	private Assessment assesment;
	
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "learningActivity",cascade = CascadeType.ALL)
	private Set<UserActivity> userActivity = new HashSet<UserActivity>();
	
	
//	public void addUserActivity(UserActivity userActivity) {
//		this.userActivity.add(userActivity);
//	}

}
