package com.capgemini.tlta.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="learning_activity")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
	
	@OneToOne
	@JoinColumn(name="assesment_id")
	private Assessment assesment;
	
	
}
