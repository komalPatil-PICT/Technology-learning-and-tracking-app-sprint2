package com.capgemini.tlta.sevice;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.UserActivity;

public interface UserActivityService {

	//public Integer uploadCerificate(String filePath,Integer userId,Integer activityId) throws ActivityException;
	//public UserActivity updateStatus(UserActivity userActivity,String status) throws ActivityException;
	public UserActivity userRegisterToLearningActivity(UserActivityDO userActivityDo) throws ActivityException;
	public UserActivity getUserActivityById(Integer id)throws ActivityException;

}
