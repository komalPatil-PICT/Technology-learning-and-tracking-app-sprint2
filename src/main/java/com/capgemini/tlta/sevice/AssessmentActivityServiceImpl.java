package com.capgemini.tlta.sevice;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.repository.AssessmentActivityRepository;

/**
 * The Class AssessmentActivityServiceImpl.
 */
@Service( value = "assessmentActivityService")
@Transactional
public class AssessmentActivityServiceImpl implements AssessmentActivityService{
	
	@Autowired
	AssessmentActivityRepository assessmentActivityRepository;
	
	/**
	 * Adds the assessment activity.
	 *
	 * @param assessmentActivity the assessment activity
	 * @return the assessment
	 * @throws AssesmentException the assesment exception
	 */
	@Override
	public Assessment addAssessmentActivity(Assessment assessmentActivity) throws AssesmentException {
		
		try {
			Assessment assessment = new Assessment();
			assessment = assessmentActivityRepository.save(assessmentActivity);
			return assessment;
		}catch(DataAccessException e) {
			throw new AssesmentException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AssesmentException(e.getMessage(),e);
		}

	}
	

	/**
	 * Search assessment activity by id.
	 *
	 * @param id the id
	 * @return the assessment
	 * @throws AssesmentException the assesment exception
	 */
	@Override
	public Assessment searchAssessmentActivityById(Integer id) throws AssesmentException {
		
		try {
			Optional<Assessment> optional= 
					assessmentActivityRepository.findById(id);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException e) {
			throw new AssesmentException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AssesmentException(e.getMessage(),e);
		}
	}
	

	/**
	 * Delete assessment activity.
	 *
	 * @param id the id
	 * @return the integer
	 * @throws AssesmentException the assesment exception
	 */
	@Override
	public Integer deleteAssessmentActivity(Integer id) throws AssesmentException {
		try {
			assessmentActivityRepository.deleteById(id);
			return 1;
		}catch(DataAccessException e) {
			throw new AssesmentException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AssesmentException(e.getMessage(),e);
		}
	}

	@Override
	public List<Assessment> getAllAssessmentActivity() throws AssesmentException {
		try {
			List<Assessment>assessementList=
					assessmentActivityRepository.findAll();
			return assessementList;
		}catch(DataAccessException e) {
			throw new AssesmentException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AssesmentException(e.getMessage(),e);
		}
	}

	/**
	 * Update assessment activity.
	 *
	 * @param assessmentActivity the assessment activity
	 * @return the assessment
	 * @throws AssesmentException the assesment exception
	 */
	@Override
	public Assessment updateAssessmentActivity(Assessment assessmentActivity) throws AssesmentException {
		try {
			Assessment p= 
					assessmentActivityRepository.save(assessmentActivity);
			return p;
		}catch(DataAccessException e) {
			throw new AssesmentException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AssesmentException(e.getMessage(),e);
		}
	}

	}
	
	
	

