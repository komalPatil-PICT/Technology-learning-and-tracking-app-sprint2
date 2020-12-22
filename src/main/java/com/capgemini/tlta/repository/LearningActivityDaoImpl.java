package com.capgemini.tlta.repository;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.sevice.AssessmentActivityService;
import com.capgemini.tlta.sevice.AssessmentActivityServiceImpl;

public class LearningActivityDaoImpl{
	private EntityManagerFactory emf= 
			Persistence.createEntityManagerFactory("technology-learning-and-tracking-app");
	private static Scanner scanner = new Scanner(System.in);
	private static AssessmentActivityService assessmentActivivtyBoundry = new AssessmentActivityServiceImpl();
	
	public Integer addLearningActivity(LearningActivity learningActivity) throws PersistenceException, AssesmentException {
		EntityManager entityManager=emf.createEntityManager();
		try {			
			entityManager.getTransaction().begin();
//			if(!entityManager.contains(learningActivity)) {
//				throw new PersistenceException(learningActivity+ "is detached state");
			//}
			System.out.println("Enter assessment Id : ");
			int assessmentId = Integer.parseInt(scanner.nextLine());
			Assessment newAssessment = assessmentActivivtyBoundry.searchAssessmentActivityById(assessmentId);
			System.out.println(newAssessment);
			learningActivity.setAssesment(newAssessment);
			entityManager.persist(learningActivity);
			//entityManager.flush();
			entityManager.getTransaction().commit();	
			
		}catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
		finally {
			entityManager.close();
		}
		return learningActivity.getId();
	}

	
	public LearningActivity searchLearningActivityById(Integer id) throws PersistenceException {
		EntityManager entityManager=emf.createEntityManager();
		try {
			//			entityManager.getTransaction().begin(); 
			LearningActivity learningActivity=
					entityManager.find(LearningActivity.class, id);			
			//			entityManager.getTransaction().commit();
			return learningActivity;
		}catch(PersistenceException e) {
			//			entityManager.getTransaction().rollback();
			throw e;
		}catch(Exception e) {
			throw e;
		}finally {
			entityManager.close();
		}	

	}
	
	public Integer deleteLearningActivity(Integer id) throws PersistenceException {
		EntityManager entityManager=emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			LearningActivity learningActivity= 
					entityManager.find(LearningActivity.class, id);
			entityManager.remove(learningActivity);
			//			entityManager.flush();
			entityManager.getTransaction().commit();	
			return learningActivity.getId();
		}catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			throw e;
		}catch(Exception e) {
			throw e;
		}finally {
			entityManager.close();
		}	
	}

	
	
	public List<LearningActivity> getAllLearningActivity() throws PersistenceException {
		EntityManager entityManager=emf.createEntityManager();

		String jql= "From LearningActivity a";
		try {			
			entityManager.getTransaction().begin();
			TypedQuery<LearningActivity> query=
					entityManager.createQuery(jql, LearningActivity.class);
			List<LearningActivity> activityList=query.getResultList();
			entityManager.getTransaction().commit();			
			return activityList;
		}catch(PersistenceException e) {
			entityManager.getTransaction().rollback();		

			throw e;
		}catch(Exception e) {
			throw e;
		}finally {
			entityManager.close();
		}
	}

	
	public LearningActivity updateLearningActivity(LearningActivity learningActivity) throws PersistenceException {
		EntityManager entityManager=emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			LearningActivity updateLearningActivity= 
					entityManager.merge(learningActivity);			
			entityManager.flush();
			entityManager.getTransaction().commit();	
			return updateLearningActivity;
		}catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			throw e;
		}catch(Exception e) {
			throw e;
		}finally {
			entityManager.close();
		}	
	}
}

