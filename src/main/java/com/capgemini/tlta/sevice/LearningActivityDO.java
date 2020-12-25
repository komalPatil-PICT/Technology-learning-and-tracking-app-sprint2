package com.capgemini.tlta.sevice;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class LearningActivityDO {
	
	private String activityName;

	private String activityLink;

	private String activityLevel;

	private Double activityTime;

	private Date activityReleaseDate;
	
	private Integer assessmentId;
}
