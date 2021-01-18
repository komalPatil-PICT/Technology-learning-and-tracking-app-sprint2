package com.capgemini.tlta.sevice;

import com.capgemini.tlta.model.ForgotPassword;
import com.capgemini.tlta.model.LogOutPayload;
import com.capgemini.tlta.model.Login;

/**
 * The Interface LoginService.
 */
public interface LoginService {

	/**
	 * Sign in.
	 *
	 * @param registerUser the register user
	 * @return the string
	 */
	public String signIn(Login registerUser);
	
	/**
	 * Sign out.
	 *
	 * @param registerUser the register user
	 * @return the string
	 */
	public String signOut(LogOutPayload registerUser);
	
	/**
	 * Change password.
	 *
	 * @param registerUser the register user
	 * @param new_password the new password
	 * @return the string
	 */
	public String changePassword(Login registerUser, String new_password);
	
	public String forgotPassword(ForgotPassword registerUser, String new_password);

}