//package com.capgemini.tlta.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.capgemini.tlta.exception.ActivityException;
//import com.capgemini.tlta.exception.AssesmentException;
//import com.capgemini.tlta.model.Assessment;
//import com.capgemini.tlta.model.UserActivity;
//import com.capgemini.tlta.sevice.UserActivityService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@Api
//@RestController
//@RequestMapping("/api/userActivity")
//public class UserActivityController {
//
//	@Autowired(required = false)
//	@Qualifier(value = "userActivityService")
//	private UserActivityService userActivityService;
//	//get assessments by Id
//		//http://localhost:8081/springfox/api/userActivity/1
//		@ApiOperation(value = "Upload Cerificate",
//				response = UserActivity.class,
//				tags = "uploadCerificate",
//				consumes = "userId",
//				httpMethod = "POST") 
//		@GetMapping("/{id}")
//		public Integer uploadCerificate(@RequestBody UserActivity userActivity,Integer userId, Integer activityId ){
//			try {
//				Integer activity= userActivityService.userRegisterToLearningActivity(userActivity,userId,activityId);
////				log.info("Product added"+ product);
//				return activity;
//			}catch(ActivityException e) {
////				log.error(e.getMessage());
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
//			}
//		}
//		
//		//http://localhost:8081/springfox/api/products/
//		//update product
//		@ApiOperation(value = "Update Status",
//				response = UserActivity.class,
//				tags = "update-Status",
//				consumes = "userActivity object and status",
//				httpMethod = "PUT") 
//		@PutMapping("/")
//		public ResponseEntity<UserActivity> updateStatus(@RequestBody UserActivity userActivity, String status) {
//			try {
//				UserActivity updatedStatus= userActivityService.updateStatus(userActivity, status);
////				log.info("Product: "+ product.getProductId()+ " updated");
//				return new ResponseEntity<>(updatedStatus,HttpStatus.OK);
//
//			}catch(ActivityException e) {
////				log.error(e.getMessage());
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
//			} 
//		}
//		//http://localhost:8081/springfox/api/userActivity/1
//				@ApiOperation(value = "User register to learning activity",
//						response = UserActivity.class,
//						tags = "register to learning activity",
//						consumes = "userActivity object, userId and activityId",
//						httpMethod = "POST") 
//				@GetMapping("/{id}")
//				//?????????? getMapping user id or activity id
//				public Integer userRegisterToLearningActivity(@RequestBody UserActivity userActivity,Integer userId, Integer activityId ){
//					try {
//						Integer registerActivity= userActivityService.userRegisterToLearningActivity(userActivity,userId,activityId);
////						log.info("Product added"+ product);
//						return registerActivity;
//					}catch(ActivityException e) {
////						log.error(e.getMessage());
//						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
//					}
//				}
//
//	}
