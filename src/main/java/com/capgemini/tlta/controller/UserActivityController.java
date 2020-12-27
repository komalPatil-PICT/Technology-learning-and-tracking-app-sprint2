package com.capgemini.tlta.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.UserActivity;
import com.capgemini.tlta.sevice.UserActivityDO;
import com.capgemini.tlta.sevice.UserActivityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class LearningActivityController.
 */
@Api
@RestController
@RequestMapping("/api/userActivity")
public class UserActivityController {
	
	@Autowired(required = false)
	@Qualifier(value = "userActivityService")
	private UserActivityService userActivityService;

	/**
	 * Gets the learning activity by id.
	 *
	 * @param id the id
	 * @return the learning activity by id
	 */
	// http://localhost:8081/springfox/api/userActivity/1
	@ApiOperation(value = "Get User Activities By Id", 
			response = UserActivity.class, 
			tags = "get-User-Activity", 
			consumes = "UserActivityId", 
			httpMethod = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<UserActivity> getUserActivityById(@PathVariable Integer id) {
		try {
			UserActivity userActivity = userActivityService.getUserActivityById(id);
			return new ResponseEntity<>(userActivity, HttpStatus.OK);
		} catch (ActivityException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	/**
	 * Gets the all learning activity.
	 *
	 * @return the all learning activity
	 */
//	// http://localhost:8081/springfox/api/learningActivity/getall/
//	@ApiOperation(value = "Get All User Activity", 
//			response = UserActivity.class, 
//			tags = "get-All-Use-Activity", 
//			httpMethod = "GET")
//
//	@GetMapping("getall/")
//	public ResponseEntity<List<UserActivity>> getAllUserActivity() {
//		try {
//			List<UserActivity> userActivityList = userActivityService.getAllUserActivities();
//			return new ResponseEntity<>(userActivityList, HttpStatus.OK);
//		} catch (ActivityException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//		}
//	}

	/**
	 * Adds the learning activity.
	 *
	 * @param learningActivity the learning activity
	 * @return the string
	 */
	// http://localhost:8081/springfox/api/userActivity/
	@ApiOperation(value = "Add a User Activity", 
			response = String.class, 
			tags = "add-User-Activity", 
			consumes = "receives UserActivity object as request body", 
			httpMethod = "POST")

	@PostMapping("/")
	public String addUserLearningActivity(@Valid @RequestBody UserActivityDO userActivityDo) {
		try {
			UserActivity status = userActivityService.userRegisterToLearningActivity(userActivityDo);
			if (status != null) {
				return "User Activity " + userActivityDo.getUserId() + " added to database";
			} else {
				return "Unable to add product to database";
			}

		} catch (ActivityException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}

