package com.capgemini.tlta.sevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.tlta.exception.OperationFailedException;
import com.capgemini.tlta.exception.ResourceNotFound;
import com.capgemini.tlta.model.LogOutPayload;
import com.capgemini.tlta.model.Login;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.repository.LoginRepository;
import static com.capgemini.tlta.exception.AppConstants.OPERATION_FAILED;
import static com.capgemini.tlta.exception.AppConstants.USER_NOT_FOUND;
import static com.capgemini.tlta.exception.AppConstants.WRONG_PASSWORD;

import java.util.Optional;


/**
 * 
 * @author Sandhya 
 *
 */

//@Service annotation is used to mark the class as a service provider
@Service
public class LoginServiceImpl implements LoginService {

	// LoginServiceimpl should implement all the methods present in LoginService
	// interface

	@Autowired // To get a relation with User repository
	private LoginRepository loginRepository;

	// **************//
	/*
	 * signin the page by User using Id and the password
	 */
	@Override
	public String signIn(Login registerUser) {
		String str = null;
		Optional<RegisterUser> userObj = loginRepository.findById(registerUser.getId());
		if (!userObj.isPresent()) {
			System.out.println(userObj);
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String pwd = userObj.get().getPassword();
			if (!pwd.equals(registerUser.getPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
			}
			try {
				str = "Sign in sucessfull";
				loginRepository.saveAndFlush(userObj.get());
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}

	// **************//
	/*
	 * signout the page by User using Id and the password
	 */
	@Override
	public String signOut(LogOutPayload registerUser) {
		String str = null;
		Optional<RegisterUser> userObj = loginRepository.findById(1);
		if (!userObj.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			try {
				str = "Sign Out sucessfull";
				loginRepository.saveAndFlush(userObj.get());
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}


	// **************//
	/*
	 * reset password of User
	 */
	@Override
	public String changePassword(Login registerUser, String new_password) {
		String str = null;
		Optional<RegisterUser> userObj = loginRepository.findById(registerUser.getId());
		if (!userObj.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String pwd = userObj.get().getPassword();
			if (!pwd.equals(registerUser.getPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
			}
			try {
				userObj.get().setPassword(new_password);
				loginRepository.saveAndFlush(userObj.get());
				str = "Password changed sucessfully";
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}
}