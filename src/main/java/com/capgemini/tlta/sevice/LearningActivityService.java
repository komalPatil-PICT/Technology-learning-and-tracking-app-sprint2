package com.capgemini.tlta.sevice;

import java.util.List;

import javax.persistence.PersistenceException;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.LearningActivity;

public interface LearningActivityService {
//	public Integer addLearningActivity(LearningActivity learningActivity) throws ActivityException; 
	public Integer addLearningActivity(LearningActivity learningActivity) throws PersistenceException, AssesmentException, ActivityException;
	public LearningActivity searchLearningActivityById(Integer id) throws ActivityException;
	public Integer deleteLearningActivity( Integer id) throws ActivityException; 
	public List<LearningActivity> getAllLearningActivity() throws ActivityException; 
	public LearningActivity updateLearningActivity(LearningActivity learningActivity) throws ActivityException;

}
