package com.capgemini.tlta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.tlta.exception.RegisterUserException;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.sevice.RegisterUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
//@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/users")
public class RegisterUserController {
	@Autowired(required = false)
	@Qualifier(value = "registerUserService")
	private RegisterUserService userService;

	// get user by Id
	// http://localhost:8081/springfox/api/users/1
	@ApiOperation(value = "Get user By Id", 
			response = RegisterUser.class, 
			tags = "get-register-user", 
			consumes = "userId", 
			httpMethod = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<RegisterUser> getRegisterUserById(@PathVariable Integer id) {
		try {
			RegisterUser user = userService.getUserById(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (RegisterUserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// get all users
	// http://localhost:8081/springfox/api/users/
	@ApiOperation(value = "Get All users", 
			response = RegisterUser.class, 
			tags = "get-RegisterUser", 
			httpMethod = "GET")

	@GetMapping("/")
	public ResponseEntity<List<RegisterUser>> getAllRegisterUsers() {
		try {
			List<RegisterUser> usersList = userService.getAllRegisteredUser();

			return new ResponseEntity<>(usersList, HttpStatus.OK);
		} catch (RegisterUserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	//http://localhost:8081/springfox/api/users/
		//add assessment	
		@ApiOperation(value = "Add User",
				response = String.class,
				tags = "Add-User",
				consumes = "receives RegisterUser object as request body",
				httpMethod = "POST") 
		
		@PostMapping("/")
		public String addRegisterUser(@RequestBody RegisterUser user) {
			try {
				RegisterUser status= userService.addUser(user);
				if(status != null) {
					return "user:"+user.getFirstName()+" "+user.getLastName()+" added to database";
				}else {
					return "Unable to add user to database";
				}

			}catch(RegisterUserException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
		
		//http://localhost:8081/springfox/api/users/1
		//delete user
		@ApiOperation(value = "Delete user By Id",
				response = String.class,
				tags = "delete-user",
				consumes = "user Id",
				httpMethod = "DELETE") 
		
		@DeleteMapping("/{id}")
		public String deleteRegisterUser(@PathVariable Integer id) {
			try {
				Integer status= userService.deleteUser(id);
				if(status ==1) {
					return "RegisterUser: "+id+" deleted from database";
				}else {
					return "Unable to delete RegisterUser from database";
				}

			}catch(RegisterUserException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}

		//TODO: error Invalid CORS Request
//		//http://localhost:8081/springfox/api/users/firstname/lastname/
//		//update user
//		@ApiOperation(value = "Update User",
//				response = RegisterUser.class,
//				tags = "update-User",
//				consumes = "RegisterUser object sents as response body",
//				httpMethod = "PUT") 
//		@PutMapping("/{firstName}/{lastName}")
//		public ResponseEntity<RegisterUser> updatePassword(@RequestBody RegisterUser user,@PathVariable String firstName,@PathVariable String lastName) {
//			try {
//				RegisterUser updatedUser= userService.updatePassword(user, firstName, lastName);
//				return new ResponseEntity<>(updatedUser,HttpStatus.OK);
//
//			}catch(RegisterUserException e) {
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
//			}
//		}
}
