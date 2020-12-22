package com.capgemini.tlta.sevice;

import java.util.List;

import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.Assessment;

public interface AssessmentActivityService {
	public Assessment addAssessmentActivity(Assessment assessmentActivity) throws AssesmentException; 
	public Assessment searchAssessmentActivityById(Integer id) throws AssesmentException;
	public Integer deleteAssessmentActivity(Integer id) throws AssesmentException;
	public List<Assessment> getAllAssessmentActivity() throws AssesmentException; 
	public Assessment updateAssessmentActivity(Assessment assessmentActivity) throws AssesmentException;
  
}
