package com.capgemini.tlta.sevice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class UserActivityDO.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityDO {

	private Integer userId;

	private Integer learningActivityId;

	private String status = "register";

	private String certificate;
}
