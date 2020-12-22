package com.capgemini.tlta.sevice;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.repository.AssessmentActivityDaoImpl;
import com.capgemini.tlta.repository.AssessmentActivityRepository;


//@Primary
@Service( value = "assessmentActivityService")
@Transactional
public class AssessmentActivityServiceImpl implements AssessmentActivityService{
	
	@Autowired
	AssessmentActivityRepository assessmentActivityRepository;
	
	@Override
	public Assessment addAssessmentActivity(Assessment assessmentActivity) throws AssesmentException {
		// TODO Auto-generated method stub
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
	
	
	

