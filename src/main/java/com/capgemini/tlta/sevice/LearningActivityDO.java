package com.capgemini.tlta.sevice;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class LearningActivityDO.
 */
@Getter
@Setter

/**
 * Instantiates a new learning activity DO.
 */
@NoArgsConstructor
public class LearningActivityDO {

	/**
	 * Instantiates a new learning activity DO.
	 *
	 * @param activityName the activity name
	 */
	public LearningActivityDO(String activityName) {
		this.activityName = activityName;
	}

	private String activityName;

	private String activityLink;

	private String activityLevel;

	private Double activityTime;

	private Date activityReleaseDate;

	private Integer assessmentId;
}
