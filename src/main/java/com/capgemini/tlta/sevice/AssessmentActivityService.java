package com.capgemini.tlta.sevice;

import java.util.List;

import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.Assessment;

/**
 * The Interface AssessmentActivityService.
 */
public interface AssessmentActivityService {
	
	/**
	 * Adds the assessment activity.
	 *
	 * @param assessmentActivity the assessment activity
	 * @return the assessment
	 * @throws AssesmentException the assesment exception
	 */
	public Assessment addAssessmentActivity(Assessment assessmentActivity) throws AssesmentException; 
	
	/**
	 * Search assessment activity by id.
	 *
	 * @param id the id
	 * @return the assessment
	 * @throws AssesmentException the assesment exception
	 */
	public Assessment searchAssessmentActivityById(Integer id) throws AssesmentException;
	
	/**
	 * Delete assessment activity.
	 *
	 * @param id the id
	 * @return the integer
	 * @throws AssesmentException the assesment exception
	 */
	public Integer deleteAssessmentActivity(Integer id) throws AssesmentException;
	public List<Assessment> getAllAssessmentActivity() throws AssesmentException; 
	
	/**
	 * Update assessment activity.
	 *
	 * @param assessmentActivity the assessment activity
	 * @return the assessment
	 * @throws AssesmentException the assesment exception
	 */
	public Assessment updateAssessmentActivity(Assessment assessmentActivity) throws AssesmentException;
  
}
