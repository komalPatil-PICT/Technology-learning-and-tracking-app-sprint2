package com.capgemini.tlta.sevice;

import com.capgemini.tlta.model.LogOutPayload;
import com.capgemini.tlta.model.Login;

public interface LoginService {
	/**
	 * Sign in customer
	 * 
	 * @param customerMaster
	 * @return sign in successful else throw invalid customer
	 */
	public String signIn(Login registerUser);

	/**
	 * Sign out
	 * 
	 * @param customerMaster
	 * @return sign out successful
	 */

	public String signOut(LogOutPayload registerUser);

	/**
	 * Change Password
	 * 
	 * @param customerMaster
	 * @param new_password
	 * @return changed password
	 */

	public String changePassword(Login registerUser, String new_password);

}