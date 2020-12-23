package com.capgemini.tlta.sevice;
import java.util.List;
import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.LearningActivity;

public interface LearningActivityService { 
	public LearningActivity addLearningActivity(LearningActivity learningActivity) throws ActivityException;
	public LearningActivity searchLearningActivityById(Integer id) throws ActivityException;
	public Integer deleteLearningActivity( Integer id) throws ActivityException; 
	public List<LearningActivity> getAllLearningActivity() throws ActivityException; 
	public LearningActivity updateLearningActivity(LearningActivity learningActivity, Integer id) throws ActivityException;

}
