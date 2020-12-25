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

/**
 * The Class LearningActivityServiceImpl.
 */
@Service( value = "learningActivityService")
@Transactional
public class LearningActivityServiceImpl implements LearningActivityService{

	@Autowired
	LearningActivityRepository learningActivityRepository;
	@Autowired
	AssessmentActivityRepository assessmentRepository;
	
	/**
	 * Adds the learning activity.
	 *
	 * @param learningActivity the learning activity
	 * @return the learning activity
	 * @throws PersistenceException the persistence exception
	 * @throws ActivityException the activity exception
	 */
	@Override
	public LearningActivity addLearningActivity(LearningActivity learningActivity)
			throws PersistenceException,ActivityException {
		LearningActivity learning = null;
		try {
			learning = learningActivityRepository.save(learningActivity);
			return learning;
		}catch(DataAccessException e) {
			throw new ActivityException(e.getMessage(),e);
		}catch(Exception e) {
			throw new  ActivityException(e.getMessage(),e);
		}
	}

	/**
	 * Search learning activity by id.
	 *
	 * @param id the id
	 * @return the learning activity
	 * @throws ActivityException the activity exception
	 */
	@Override
	public LearningActivity searchLearningActivityById(Integer id) throws ActivityException {
		try {
			Optional<LearningActivity> optional= 
					learningActivityRepository.findById(id);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				//System.out.println(optional.get().getAssesment().getId());
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

	/**
	 * Delete learning activity.
	 *
	 * @param id the id
	 * @return the integer
	 * @throws ActivityException the activity exception
	 */
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
	
	/**
	 * Update learning activity.
	 *
	 * @param learningActivity the learning activity
	 * @return the learning activity
	 * @throws ActivityException the activity exception
	 */
	@Override
	public LearningActivity updateLearningActivity(LearningActivity learningActivity) throws ActivityException {
		
		try {
			LearningActivity learningAct= 
					learningActivityRepository.save(learningActivity);
			return learningAct;
	}catch(DataAccessException e) {
		throw new ActivityException(e.getMessage(),e);
	}catch(Exception e) {
		throw new ActivityException(e.getMessage(),e);
	}
	}

	/**
	 * Adds the learning activity with assessment.
	 *
	 * @param learningActivity the learning activity
	 * @param id the id
	 * @return the learning activity
	 * @throws ActivityException the activity exception
	 */
	@Override
	public LearningActivity addLearningActivityWithAssessment(LearningActivityDO learningActivityDo)
			throws ActivityException {
		
		System.out.println(learningActivityDo.getActivityName());
		
		Assessment assessment = null;
		LearningActivity learning = new LearningActivity(learningActivityDo);
		
		try {
			assessment = assessmentRepository.getOne(learningActivityDo.getAssessmentId());
			learning.setAssesment(assessment);
			learning.setActivityName(learningActivityDo.getActivityName());
			learning = learningActivityRepository.save(learning);
			return learning;
		}catch(DataAccessException e) {
			throw new ActivityException(e.getMessage(),e);
		}catch(Exception e) {
			throw new  ActivityException(e.getMessage(),e);
		}
	}

//	@Override
//	public LearningActivity updateLearningActivity(LearningActivity learningActivity) throws ActivityException {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
