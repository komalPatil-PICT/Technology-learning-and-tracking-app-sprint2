package com.capgemini.tlta.repository;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.LearningActivity;

@Repository
public interface LearningActivityRepository extends JpaRepository<LearningActivity, Integer>{

//	public Integer addLearningActivity(LearningActivity learningActivity) throws PersistenceException, AssesmentException; 
//	public LearningActivity searchLearningActivityById(Integer id) throws PersistenceException;
//	public Integer deleteLearningActivity( Integer id) throws PersistenceException; 
//	public List<LearningActivity> getAllLearningActivity() throws PersistenceException; 
//	public LearningActivity updateLearningActivity(LearningActivity learningActivity) throws PersistenceException;
//	
}
