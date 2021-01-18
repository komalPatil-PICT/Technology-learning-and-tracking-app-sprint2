package com.capgemini.tlta.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.tlta.exception.OperationFailedException;
import com.capgemini.tlta.exception.ResourceNotFound;
import com.capgemini.tlta.model.ForgotPassword;
import com.capgemini.tlta.model.LogOutPayload;
import com.capgemini.tlta.model.Login;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.model.Role;
import com.capgemini.tlta.repository.LoginRepository;
import static com.capgemini.tlta.exception.AppConstants.OPERATION_FAILED;
import static com.capgemini.tlta.exception.AppConstants.USER_NOT_FOUND;
import static com.capgemini.tlta.exception.AppConstants.WRONG_PASSWORD;

import java.util.Optional;

/**
 * The Class LoginServiceImpl.
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	/**
	 * Sign in.
	 *
	 * @param registerUser the register user
	 * @return the string
	 */
	@Override
	public String signIn(Login registerUser) {
		String str = null;
		Optional<RegisterUser> userObj = loginRepository.findByEmailId(registerUser.getEmail());
		if (!userObj.isPresent()) {
			System.out.println(userObj);
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String pwd = userObj.get().getPassword();
			Role role=userObj.get().getRole();
			if (!pwd.equals(registerUser.getPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
			}if(!role.equals(registerUser.getRole())) {
				throw new ResourceNotFound(USER_NOT_FOUND);
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

	/**
	 * Sign out.
	 *
	 * @param registerUser the register user
	 * @return the string
	 */
	@Override
	public String signOut(LogOutPayload registerUser) {
		String str = null;
		Optional<RegisterUser> userObj = loginRepository.findByEmailId(registerUser.getEmail());
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

	/**
	 * Change password.
	 *
	 * @param registerUser the register user
	 * @param new_password the new password
	 * @return the string
	 */
	@Override
	public String changePassword(Login registerUser, String new_password) {
		String str = null;
		Optional<RegisterUser> userObj = loginRepository.findByEmailId(registerUser.getEmail());
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
	
	@Override
	public String forgotPassword(ForgotPassword registerUser, String newPassword) {
		String str = null;
		Optional<RegisterUser> userObj = loginRepository.findByEmailId(registerUser.getEmail());
		
		if (!userObj.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String lastName = userObj.get().getLastName();
			if (!lastName.equals(registerUser.getlastName())) {
				throw new ResourceNotFound(USER_NOT_FOUND);
			}
			try {
				userObj.get().setPassword(newPassword);
				loginRepository.saveAndFlush(userObj.get());
				str = "Password updated sucessfully";
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}
	
	
}