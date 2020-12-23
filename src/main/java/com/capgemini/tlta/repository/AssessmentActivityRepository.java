package com.capgemini.tlta.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.Assessment;

@Repository
public interface AssessmentActivityRepository extends JpaRepository<Assessment, Integer>{

     
	//	public Integer addAssessmentActivity(Assessment assessmentActivity) throws PersistenceException; 
//	public Assessment searchAssessmentActivityById(Integer id) throws PersistenceException;
//	public Integer deleteAssessmentActivity(Integer id) throws PersistenceException; 
//	public List<Assessment> getAllAssessmentActivity() throws PersistenceException; 
//	public Assessment updateAssessmentActivity(Assessment assessmentActivity) throws PersistenceException;
//	
}

