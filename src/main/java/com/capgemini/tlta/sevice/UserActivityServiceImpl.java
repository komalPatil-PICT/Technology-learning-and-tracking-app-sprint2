package com.capgemini.tlta.sevice;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.model.UserActivity;
import com.capgemini.tlta.repository.LearningActivityRepository;
import com.capgemini.tlta.repository.RegisterUserRepository;
import com.capgemini.tlta.repository.UserActivityRepository;

/**
 * The Class UserActivityServiceImpl.
 */
@Service(value = "userActivityService")
@Transactional
public class UserActivityServiceImpl implements UserActivityService {

	@Autowired
	LearningActivityRepository learningActivityRepository;

	@Autowired
	RegisterUserRepository registerUserRepository;

	@Autowired
	UserActivityRepository userActivityRepository;

	/**
	 * User register to learning activity.
	 *
	 * @param userActivityDo the user activity do
	 * @return the user activity
	 * @throws ActivityException the activity exception
	 */
	@Override
	public UserActivity userRegisterToLearningActivity(UserActivityDO userActivityDo) throws ActivityException {
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

	/**
	 * Gets the user activity by id.
	 *
	 * @param id the id
	 * @return the user activity by id
	 * @throws ActivityException the activity exception
	 */
	@Override
	public UserActivity getUserActivityById(Integer id) throws ActivityException {
		try {
			Optional<UserActivity> optional = userActivityRepository.findById(id);
			if (optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			} else {
				return null;
			}
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}

	}
	
	/**
	 * Gets the user activity by id.
	 *
	 * @param id the id
	 * @return the user activity by id
	 * @throws ActivityException the activity exception
	 */
	@Override
	public UserActivity getActivityofUserById(Integer id) throws ActivityException {
		try {
			Optional<UserActivity> optional = userActivityRepository.findById(id);
			if (optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			} else {
				return null;
			}
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}

	}	
	
	@Override
	public List<UserActivity> getAllUserActivities() throws ActivityException {
		List<UserActivity> allActivities = null;

		try {
			allActivities = userActivityRepository.findAll();
			return allActivities;
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}
	}

	/**
	 * Delete user activity by id.
	 *
	 * @param id the id
	 * @return the integer
	 * @throws ActivityException the activity exception
	 */
	@Override
	public Integer deleteUserActivityById(Integer id) throws ActivityException {
		try {
			userActivityRepository.deleteById(id);
			return 1;
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}
	}

	/**
	 * Upload cerificate.
	 *
	 * @param file the file
	 * @param id the id
	 * @return true, if successful
	 * @throws ActivityException the activity exception
	 */
	@Override
	public boolean uploadCerificate(MultipartFile file, Integer id) throws ActivityException {
		boolean isUploaded = false;
		try {
			UserActivity activity = userActivityRepository.findById(id).get();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			activity.setFile(file.getBytes());

			activity.setCertificate(fileName);
			userActivityRepository.save(activity);

			isUploaded = true;
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (IOException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}
		return isUploaded;
	}

	/**
	 * Gets the certificate name by id.
	 *
	 * @param id the id
	 * @return the certificate name by id
	 * @throws ActivityException the activity exception
	 */
	@Override
	public String getCertificateNameById(Integer id) throws ActivityException {
		try {
			UserActivity activity = userActivityRepository.findById(id).get();
			String fileName = activity.getCertificate();
			return fileName;
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}
	}

	/**
	 * Gets the certificate by id.
	 *
	 * @param id the id
	 * @return the certificate by id
	 * @throws ActivityException the activity exception
	 */
	@Override
	public byte[] getCertificateById(Integer id) throws ActivityException {
		try {
			UserActivity activity = userActivityRepository.findById(id).get();
			return activity.getFile();
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}
	}

	/**
	 * Update status by id.
	 *
	 * @param id the id
	 * @param status the status
	 * @return true, if successful
	 * @throws ActivityException the activity exception
	 */
	@Override
	public boolean updateStatusById(UserActivityStatusUpdateDo status) throws ActivityException {
		try {
			UserActivity activity = userActivityRepository.findById(status.getUserActivityId()).get();
			activity.setStatus(status.getStatus());
			return true;
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<UserActivity> findUserActivityByUserId(Integer id) throws ActivityException {
		List<UserActivity> allActivities = null;

		try {
			allActivities = userActivityRepository.findUserActivityByUserId(id);
			return allActivities;
		} catch (DataAccessException e) {
			throw new ActivityException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ActivityException(e.getMessage(), e);
		}
	}
}
