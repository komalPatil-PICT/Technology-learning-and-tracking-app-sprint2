package com.capgemini.tlta.sevice;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
public class LearningActivityDO {
	
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
