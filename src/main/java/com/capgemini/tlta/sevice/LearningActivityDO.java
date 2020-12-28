package com.capgemini.tlta.sevice;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class LearningActivityDO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LearningActivityDO {

	/**
	 * Instantiates a new learning activity DO.
	 *
	 * @param activityName the activity name
	 */
	public LearningActivityDO(String activityName) {
		this.activityName = activityName;
	}
	@NotNull
	@Size(min=3,max=30, message="Activity name should have atleast 3 characters")
	private String activityName;

	@URL
	private String activityLink;

	@NotNull(message="Activity level should be Beginner/Intermediate/Expert")
	@Size(min=6)
	private String activityLevel;

	@NotNull(message="Please provide activity duration in hours")
	private Double activityTime;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="Please provide a date in yyyy-MM-dd format")
	private Date activityReleaseDate;

	private Integer assessmentId;

	public LearningActivityDO(
			@NotNull @Size(min = 3, max = 30, message = "Activity name should have atleast 3 characters") String activityName,
			@URL String activityLink,
			@NotNull(message = "Activity level should be Beginner/Intermediate/Expert") @Size(min = 6) String activityLevel,
			@NotNull(message = "Please provide activity duration in hours") Double activityTime,
			@NotNull(message = "Please provide a date in yyyy-MM-dd format") Date activityReleaseDate) {
		super();
		this.activityName = activityName;
		this.activityLink = activityLink;
		this.activityLevel = activityLevel;
		this.activityTime = activityTime;
		this.activityReleaseDate = activityReleaseDate;
	}


	
}
