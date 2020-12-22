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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="assesment_activity")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
   
	
	
}
