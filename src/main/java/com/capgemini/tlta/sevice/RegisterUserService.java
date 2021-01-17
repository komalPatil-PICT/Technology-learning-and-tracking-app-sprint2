package com.capgemini.tlta.sevice;

import java.util.List;

import javax.mail.MessagingException;

import com.capgemini.tlta.exception.RegisterUserException;
import com.capgemini.tlta.model.RegisterUser;

/**
 * The Interface RegisterUserService.
 */
public interface RegisterUserService {

	/**
	 * Adds the user.
	 *
	 * @param login the login
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	public RegisterUser addUser(RegisterUser login) throws RegisterUserException;
	public void sendCredentialMail(RegisterUser user) throws MessagingException;
	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 * @throws RegisterUserException the register user exception
	 */
	public RegisterUser getUserById(Integer id) throws RegisterUserException;
	
	/**
	 * Gets the moderator by id.
	 *
	 * @param id the id
	 * @return the moderator by id
	 * @throws RegisterUserException the register user exception
	 */
	public RegisterUser getModeratorById(Integer id) throws RegisterUserException;
	
	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the integer
	 * @throws RegisterUserException the register user exception
	 */
	public Integer deleteUser(Integer id) throws RegisterUserException;
	
	/**
	 * Update password.
	 *
	 * @param id the id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param password the password
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	public RegisterUser updatePassword(RegisterUserChangePasswordDO userDo)
			throws RegisterUserException;
	
	/**
	 * Update first name.
	 *
	 * @param id the id
	 * @param firstName the first name
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	public RegisterUser updateFirstName(RegisterUserChangeFirstNameDo userDo) throws RegisterUserException;
	
	/**
	 * Update last name.
	 *
	 * @param login the login
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	public RegisterUser updateLastName(RegisterUser login) throws RegisterUserException;
	
	/**
	 * Gets the all registered user.
	 *
	 * @return the all registered user
	 * @throws RegisterUserException the register user exception
	 */
	public List<RegisterUser> getAllRegisteredUser() throws RegisterUserException;
	
	public RegisterUser updateUser(RegisterUser user)throws RegisterUserException;

}
