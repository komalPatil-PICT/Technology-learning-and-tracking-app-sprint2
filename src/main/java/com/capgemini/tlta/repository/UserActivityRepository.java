package com.capgemini.tlta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer>{
	
//	public Integer userRegisterToLearningActivity(UserActivity userActivity, Integer userId, Integer activityId) throws PersistenceException;
//	public Integer uploadCerificate(String filePath,Integer userId,Integer activityId) throws PersistenceException;
//	public UserActivity updateStatus(UserActivity userActivity, String status) throws PersistenceException;
}
