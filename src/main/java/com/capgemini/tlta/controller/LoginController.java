package com.capgemini.tlta.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.tlta.exception.BaseResponse;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.model.LogOutPayload;
import com.capgemini.tlta.model.Login;
import com.capgemini.tlta.sevice.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class LoginController.
 */
@RestController
@RequestMapping("/api/Login")
@Api(value = "RegisterUser")
@CrossOrigin("http://localhost:3000")

public class LoginController {

	@Autowired 
	private LoginService loginService;

	/**
	 * Sign in.
	 *
	 * @param registerUser the register user
	 * @return the response entity
	 */
	//http://localhost:8081/springfox/api/Login/login
	@ApiOperation(value = "SignIn")
	@PostMapping("/login")
	public ResponseEntity<?> signIn( @RequestBody Login registerUser) {
		String str = loginService.signIn(registerUser);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	/**
	 * Sign out.
	 *
	 * @param registerUser the register user
	 * @return the response entity
	 */
	//http://localhost:8081/springfox/api/Login/logout
	@PostMapping("/logout") 
	@ApiOperation(value = "SignOut")
	public ResponseEntity<?> signOut( @RequestBody LogOutPayload registerUser) {
		String str = loginService.signOut(registerUser);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	
	//TODO:not working
	/**
	 * Change password.
	 *
	 * @param registerUser the register user
	 * @param new_password the new password
	 * @return the response entity
	 */
	//http://localhost:8081/springfox/api/Login/reset/newPass
	@PostMapping("/reset/{new_password}")
	@ApiOperation(value = "Reset Password")
	public ResponseEntity<?> changePassword( @RequestBody Login registerUser, @PathVariable String new_password) {
		String str =loginService.changePassword(registerUser, new_password);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

}