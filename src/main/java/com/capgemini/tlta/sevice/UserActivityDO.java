package com.capgemini.tlta.sevice;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class UserActivityDO.
 */
@Getter 
@Setter
public class UserActivityDO {
	
	private Integer userId;
	
	private Integer learningActivityId;
	
	private String status = "register";
	
	private String certificate;
}
