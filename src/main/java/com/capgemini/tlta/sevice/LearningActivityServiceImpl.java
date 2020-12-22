package com.capgemini.tlta.sevice;

import java.util.List;


import javax.persistence.PersistenceException;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.repository.LearningActivityRepository;
import com.capgemini.tlta.repository.LearningActivityDaoImpl;

public class LearningActivityServiceImpl implements LearningActivityService{

	@Override
	public Integer addLearningActivity(LearningActivity learningActivity)
			throws PersistenceException, AssesmentException, ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LearningActivity searchLearningActivityById(Integer id) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteLearningActivity(Integer id) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LearningActivity> getAllLearningActivity() throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LearningActivity updateLearningActivity(LearningActivity learningActivity) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

}
