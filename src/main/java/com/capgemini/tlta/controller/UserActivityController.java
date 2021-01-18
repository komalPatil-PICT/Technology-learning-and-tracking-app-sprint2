package com.capgemini.tlta.controller;

import java.net.URLConnection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.exception.RegisterUserException;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.model.UserActivity;
import com.capgemini.tlta.sevice.RegisterUserService;
import com.capgemini.tlta.sevice.UserActivityDO;
import com.capgemini.tlta.sevice.UserActivityService;
import com.capgemini.tlta.sevice.UserActivityStatusUpdateDo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class UserActivityController.
 */
@Api
@RestController
@RequestMapping("/api/userActivity")
@CrossOrigin("http://localhost:3000")
public class UserActivityController {
	
	@Autowired(required = false)
	@Qualifier(value = "userActivityService")
	private UserActivityService userActivityService;
	// http://localhost:8081/springfox/api/userActivity/1
	
	@Autowired
	private RegisterUserService reg;
	
	/**
	 * Gets the user activity by id.
	 *
	 * @param id the id
	 * @return the user activity by id
	 */
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
	 * Gets the user activity by id.
	 *
	 * @param id the id
	 * @return the user activity by id
	 */
	// http://localhost:8081/springfox/api/userActivity/user/1
	@ApiOperation(value = "Get User Activities By Id", 
			response = UserActivity.class, 
			tags = "get-User-Activity", 
			consumes = "UserActivityId", 
			httpMethod = "GET")
	@GetMapping("/user/{id}")
	public ResponseEntity<UserActivity> getActivityofUserById(@PathVariable Integer id) {
		try {
			RegisterUser r=reg.getUserById(id);
			System.out.println("rrrrr:"+r.getId());
			UserActivity userActivity = userActivityService.getUserActivityById(r.getId());
//			System.out.println("uuuuu"+userActivity.getStatus());
			return new ResponseEntity<>(userActivity, HttpStatus.OK);
		} catch (ActivityException | RegisterUserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} 
	}
	

	/**
	 * Adds the user learning activity.
	 *
	 * @param userActivityDo the user activity do
	 * @return the string
	 */
	@ApiOperation(value = "Add a User Activity", 
			response = String.class, 
			tags = "add-User-Activity", 
			consumes = "receives UserActivity object as request body", 
			httpMethod = "POST")
	
	// http://localhost:8081/springfox/api/userActivity/
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
	
	/**
	 * Gets the all user activity.
	 *
	 * @return the all user activity
	 */
	@ApiOperation(value = "Get All User Activity", 
			response = List.class, 
			tags = "get-All-Use-Activity", 
			httpMethod = "GET")
	
	// http://localhost:8081/springfox/api/userActivity
	@GetMapping("/")
	public ResponseEntity<List<UserActivity>> getAllUserActivity() {
		try {
			List<UserActivity> userActivityList = userActivityService.getAllUserActivities();
			return new ResponseEntity<>(userActivityList, HttpStatus.OK);
		} catch (ActivityException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
	 * Delete user activity.
	 *
	 * @param id the id
	 * @return the string
	 */
	@ApiOperation(value = "Delete UserActivity By Id",
			response = String.class,
			tags = "delete-user-activity",
			consumes = "User Activity Id",
			httpMethod = "DELETE")
	//http://localhost:8081/springfox/api/userActivity/1
	@DeleteMapping("/{id}")
	public String deleteUserActivity(@PathVariable Integer id) {
		try {
			Integer status = userActivityService.deleteUserActivityById(id);
			if (status == 1) {
				return "UserActivity: " + id + " deleted from database";
			} else {
				return "Unable to delete UserActivity from database";
			}

		} catch (ActivityException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
	 * Upload to DB.
	 *
	 * @param file the file
	 * @param id the id
	 * @return the string
	 */
	@ApiOperation(value = "Upload Certificate By userActivityId", 
			response = String.class, 
			tags = "Upload-certificate", 
			consumes = "multipart file and userActivityId", 
			httpMethod = "PUT")
	//http://localhost:8081/springfox/api/userActivity/upload/1	
	@PutMapping("/upload/{id}")
	public String uploadToDB(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
		try {
			boolean isUploaded = userActivityService.uploadCerificate(file, id);

			if (isUploaded) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/userActivity/download/" + id).toUriString();
				return fileDownloadUri;
			} else {
				return "Could not upload certificate!";
			}
		} catch (ActivityException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	
	/**
	 * Download from DB.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@ApiOperation(value = "Download certificate By userActivityId", 
			response = ResponseEntity.class, 
			tags = "Download-Certificate", 
			consumes = "UserActivityId", 
			httpMethod = "GET")
	//http://localhost:8081/springfox/api/userActivity/download/1
	@GetMapping("/download/{id}")
	public ResponseEntity downloadFromDB(@PathVariable Integer id) {
		
		try {
			String certificateName = userActivityService.getCertificateNameById(id);
			String type = URLConnection.guessContentTypeFromName(certificateName);
			byte[] certificate = userActivityService.getCertificateById(id);
			
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(type))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" 
							+ certificateName + "\"")
					.body(certificate);
	
		} catch (ActivityException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
	 * Upload to DB.
	 *
	 * @param id the id
	 * @param status the status
	 * @return the string
	 */
	@ApiOperation(value = "Update status By userActivityId", 
			response = ResponseEntity.class,
			tags = "Update-user-activity", 
			consumes = "userActivityId and updated status", 
			httpMethod = "PUT")
	//http://localhost:8081/springfox/api/userActivity/updateStatus
	
	@PutMapping("/{id}")
	public String updateStatus(@PathVariable Integer id,
			@RequestBody UserActivityStatusUpdateDo status) {
		try {
			UserActivity u = userActivityService.getUserActivityById(id);
			
			u.setUserActivityId(status.getUserActivityId()); 
			u.setStatus(status.getStatus());
			
			UserActivityStatusUpdateDo udo=null;
			Integer id1= u.getUserActivityId();
			String status1=u.getStatus();
			udo=new UserActivityStatusUpdateDo(id1,status1);
			
			boolean isUpdated = userActivityService.updateStatusById(udo);

			if (isUpdated) {
				return "Updated status successfully";
			} else {
				return "Could not upload certificate!";
			}
		} catch (ActivityException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	
	@ApiOperation(value = "Get All User Activity by user id", 
			response = List.class, 
			tags = "get-All-Use-Activity", 
			httpMethod = "GET")
	
	// http://localhost:8081/springfox/api/userActivity/byUserId/86
	@GetMapping("/byUserId/{id}")
	public ResponseEntity<List<UserActivity>> getAllUserActivityByUserId(@PathVariable Integer id) {
		try {
			List<UserActivity> userActivityList = userActivityService.findUserActivityByUserId(id);
			return new ResponseEntity<>(userActivityList, HttpStatus.OK);
		} catch (ActivityException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}

