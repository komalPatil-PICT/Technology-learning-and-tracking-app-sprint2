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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Assessment.
 */
@Entity
@Table(name = "assesment_activity")
@NoArgsConstructor
@Getter 
@Setter
public class Assessment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min=3,max=30, message="Assessment name should have atleast 3 characters")
	@Column(name = "assesment_name")
	private String assessmentName;

	@Null(message="Assessment type should be MCQ/Written Test/Coding")
	@Size(min=3)
	@Column(name = "assesment_type")
	private String assessmentType;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Null(message="Please provide a date in yyyy-MM-dd format")
	@Temporal(TemporalType.DATE)
	@Column(name = "assesment_release_date")
	private Date assessmentReleaseDate;

	@Null(message="Please provide a duration in hours")
	@Column(name = "assesment_time_duration")
	private Double assessmentTimeDuration;

	@OneToOne(mappedBy = "assesment")
	@JsonIgnore
	private LearningActivity learningActivity;

	public Assessment(String assesment_name) {
		this.assessmentName = assesment_name;
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
