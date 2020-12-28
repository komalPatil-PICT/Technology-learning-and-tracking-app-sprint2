package com.capgemini.tlta.sevice;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.UserActivity;

/**
 * The Interface UserActivityService.
 */
public interface UserActivityService {

	/**
	 * User register to learning activity.
	 *
	 * @param userActivityDo the user activity do
	 * @return the user activity
	 * @throws ActivityException the activity exception
	 */
	public UserActivity userRegisterToLearningActivity(UserActivityDO userActivityDo) throws ActivityException;

	/**
	 * Gets the user activity by id.
	 *
	 * @param id the id
	 * @return the user activity by id
	 * @throws ActivityException the activity exception
	 */
	public UserActivity getUserActivityById(Integer id) throws ActivityException;

	/**
	 * Gets the all user activities.
	 *
	 * @return the all user activities
	 * @throws ActivityException the activity exception
	 */
	public List<UserActivity> getAllUserActivities() throws ActivityException;

	/**
	 * Delete user activity by id.
	 *
	 * @param id the id
	 * @return the integer
	 * @throws ActivityException the activity exception
	 */
	public Integer deleteUserActivityById(Integer id) throws ActivityException;

	/**
	 * Upload cerificate.
	 *
	 * @param file the file
	 * @param activityId the activity id
	 * @return true, if successful
	 * @throws ActivityException the activity exception
	 */
	public boolean uploadCerificate(MultipartFile file, Integer activityId) throws ActivityException;

	/**
	 * Gets the certificate name by id.
	 *
	 * @param activityId the activity id
	 * @return the certificate name by id
	 * @throws ActivityException the activity exception
	 */
	public String getCertificateNameById(Integer activityId) throws ActivityException;

	/**
	 * Gets the certificate by id.
	 *
	 * @param activityId the activity id
	 * @return the certificate by id
	 * @throws ActivityException the activity exception
	 */
	public byte[] getCertificateById(Integer activityId) throws ActivityException;

}
