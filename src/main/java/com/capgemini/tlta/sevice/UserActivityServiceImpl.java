package com.capgemini.tlta.sevice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.model.UserActivity;
import com.capgemini.tlta.repository.LearningActivityRepository;
import com.capgemini.tlta.repository.RegisterUserRepository;
import com.capgemini.tlta.repository.UserActivityRepository;

@Service(value = "userActivityService")
@Transactional
public class UserActivityServiceImpl implements UserActivityService {

	@Autowired
	LearningActivityRepository learningActivityRepository;
	
	@Autowired
	RegisterUserRepository registerUserRepository;
	
	@Autowired
	UserActivityRepository userActivityRepository;
	
//	@Override
//	public Integer uploadCerificate(String filePath, Integer userId, Integer activityId) throws ActivityException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public UserActivity updateStatus(UserActivity userActivity, String status) throws ActivityException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public UserActivity userRegisterToLearningActivity(UserActivityDO userActivityDo)
			throws ActivityException {
		LearningActivity learning = null;
		RegisterUser register = null;
		UserActivity user = new UserActivity(userActivityDo);
		try {

			learning = learningActivityRepository.getOne(userActivityDo.getLearningActivityId());
			register = registerUserRepository.getOne(userActivityDo.getUserId());
			
			
			user.setLearningActivity(learning);
			user.setRegisterUser(register);

			user = userActivityRepository.save(user);
			return user;
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}
	}

	@Override
	public UserActivity getUserActivityById(Integer id) throws ActivityException {
		try {
			Optional<UserActivity> optional= 
					userActivityRepository.findById(id);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException e) {
			throw new ActivityException(e.getMessage(),e);
		}catch(Exception e) {
			throw new ActivityException(e.getMessage(),e);
		}
		
	}
}
