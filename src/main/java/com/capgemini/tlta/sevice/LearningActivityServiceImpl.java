package com.capgemini.tlta.sevice;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.repository.AssessmentActivityRepository;
import com.capgemini.tlta.repository.LearningActivityRepository;

@Service( value = "learningActivityService")
@Transactional
public class LearningActivityServiceImpl implements LearningActivityService{

	@Autowired
	LearningActivityRepository learningActivityRepository;
//	@Autowired
//	AssessmentActivityRepository assessmentActivityRepository;
	@Override
	public LearningActivity addLearningActivity(LearningActivity learningActivity)
			throws PersistenceException,ActivityException {
	//	Assessment assessment = null;
		//LearningActivity learning = null;
		try {
	//		assessment = assessmentActivityRepository.getOne(id);
		//	learningActivity.setAssesment(assessment);
			LearningActivity learning = new LearningActivity();
			learning = learningActivityRepository.save(learningActivity);
			return learning;
		}catch(DataAccessException e) {
			throw new ActivityException(e.getMessage(),e);
		}catch(Exception e) {
			throw new  ActivityException(e.getMessage(),e);
		}
	}

	@Override
	public LearningActivity searchLearningActivityById(Integer id) throws ActivityException {
		try {
			Optional<LearningActivity> optional= 
					learningActivityRepository.findById(id);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException e) {
			throw new ActivityException(e.getMessage(),e);
		}catch(Exception e) {
			throw new  ActivityException(e.getMessage(),e);
		}
	}

	@Override
	public Integer deleteLearningActivity(Integer id) throws ActivityException {
		try {
			learningActivityRepository.deleteById(id);
			return 1;
		}catch(DataAccessException e) {
			throw new ActivityException(e.getMessage(),e);
		}catch(Exception e) {
			throw new ActivityException(e.getMessage(),e);
		}
	}

	@Override
	public List<LearningActivity> getAllLearningActivity() throws ActivityException {
		try {
			List<LearningActivity>learningList=
					learningActivityRepository.findAll();
			return learningList;
		}catch(DataAccessException e) {
			throw new ActivityException(e.getMessage(),e);
		}catch(Exception e) {
			throw new ActivityException(e.getMessage(),e);
		}
	}
	@Autowired
	AssessmentActivityRepository assessmentActivityRepository;
	@Override
	public LearningActivity updateLearningActivity(LearningActivity learningActivity, Integer id) throws ActivityException {
		Assessment assessment = null;
		try {
			assessment = assessmentActivityRepository.getOne(id);
			learningActivity.setAssesment(assessment);

			LearningActivity learningAct= 
					learningActivityRepository.save(learningActivity);
			return learningAct;
	}catch(DataAccessException e) {
		throw new ActivityException(e.getMessage(),e);
	}catch(Exception e) {
		throw new ActivityException(e.getMessage(),e);
	}
	}
}
