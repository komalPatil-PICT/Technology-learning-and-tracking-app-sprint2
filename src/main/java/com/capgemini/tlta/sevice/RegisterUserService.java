package com.capgemini.tlta.sevice;

import java.util.List;

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
	 * @param login the login
	 * @param firstName the first name
	 * @param lastName the last name
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	public RegisterUser updatePassword(Integer id,String firstName,String lastName,String password) throws RegisterUserException;
	
	/**
	 * Update first name.
	 *
	 * @param login the login
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	public RegisterUser updateFirstName(Integer id, String firstName) throws RegisterUserException;
	
	/**
	 * Update last name.
	 *
	 * @param login the login
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	public RegisterUser updateLastName(RegisterUser login) throws RegisterUserException;
	public List<RegisterUser> getAllRegisteredUser() throws RegisterUserException;

	
	
}
