package com.capgemini.tlta.sevice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityStatusUpdateDo {

	private Integer userActivityId;	
	private String status = "register";
}
