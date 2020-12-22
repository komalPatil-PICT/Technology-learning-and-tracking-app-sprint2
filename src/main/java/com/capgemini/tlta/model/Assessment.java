package com.capgemini.tlta.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="assesment_activity")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
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
	
	@ToString.Exclude
	@OneToOne(mappedBy = "assesment")
	@JsonIgnore
    private LearningActivity learningActivity;
   	
}
