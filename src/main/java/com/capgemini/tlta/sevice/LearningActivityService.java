package com.capgemini.tlta.sevice;

import java.util.List;
import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.LearningActivity;

/**
 * The Interface LearningActivityService.
 */
public interface LearningActivityService { 
	
	/**
	 * Adds the learning activity.
	 *
	 * @param learningActivity the learning activity
	 * @return the learning activity
	 * @throws ActivityException the activity exception
	 */
	public LearningActivity addLearningActivity(LearningActivity learningActivity) throws ActivityException;
	
	/**
	 * Search learning activity by id.
	 *
	 * @param id the id
	 * @return the learning activity
	 * @throws ActivityException the activity exception
	 */
	public LearningActivity searchLearningActivityById(Integer id) throws ActivityException;
	
	/**
	 * Delete learning activity.
	 *
	 * @param id the id
	 * @return the integer
	 * @throws ActivityException the activity exception
	 */
	public Integer deleteLearningActivity( Integer id) throws ActivityException; 
	public List<LearningActivity> getAllLearningActivity() throws ActivityException; 
	
	/**
	 * Update learning activity.
	 *
	 * @param learningActivity the learning activity
	 * @return the learning activity
	 * @throws ActivityException the activity exception
	 */
	public LearningActivity updateLearningActivity(LearningActivity learningActivity) throws ActivityException;
	
	/**
	 * Adds the learning activity with assessment.
	 *
	 * @param learningActivity the learning activity
	 * @param id the id
	 * @return the learning activity
	 * @throws ActivityException the activity exception
	 */
	public LearningActivity addLearningActivityWithAssessment(LearningActivityDO learningActivityDo) throws ActivityException;
}
