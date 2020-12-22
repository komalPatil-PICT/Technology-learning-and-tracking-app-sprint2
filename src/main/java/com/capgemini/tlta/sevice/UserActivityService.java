package com.capgemini.tlta.sevice;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.UserActivity;

public interface UserActivityService {

	public Integer uploadCerificate(String filePath,Integer userId,Integer activityId) throws ActivityException;
	public UserActivity updateStatus(UserActivity userActivity,String status) throws ActivityException;
	public Integer userRegisterToLearningActivity(UserActivity userActivity, Integer userId, Integer activityId) throws ActivityException;
}
