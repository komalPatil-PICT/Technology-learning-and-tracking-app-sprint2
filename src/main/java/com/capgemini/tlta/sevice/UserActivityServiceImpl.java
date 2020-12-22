package com.capgemini.tlta.sevice;

import javax.persistence.PersistenceException;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.UserActivity;
import com.capgemini.tlta.repository.UserActivityRepository;
import com.capgemini.tlta.repository.UserActivityDaoImpl;


public class UserActivityServiceImpl implements UserActivityService{

	@Override
	public Integer uploadCerificate(String filePath, Integer userId, Integer activityId) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserActivity updateStatus(UserActivity userActivity, String status) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer userRegisterToLearningActivity(UserActivity userActivity, Integer userId, Integer activityId)
			throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
