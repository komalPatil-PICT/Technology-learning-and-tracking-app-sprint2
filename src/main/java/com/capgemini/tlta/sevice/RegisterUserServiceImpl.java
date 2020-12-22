package com.capgemini.tlta.sevice;

import java.util.List;

import javax.persistence.PersistenceException;

import com.capgemini.tlta.exception.RegisterUserException;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.repository.RegisterUserRepository;
import com.capgemini.tlta.repository.RegisterUserDaoImpl;

public class RegisterUserServiceImpl implements RegisterUserService {

	@Override
	public void addUser(RegisterUser login) throws RegisterUserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RegisterUser getUserById(Integer id) throws RegisterUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisterUser getModeratorById(Integer id) throws RegisterUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteUser(Integer id) throws RegisterUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisterUser updatePassword(RegisterUser login) throws RegisterUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisterUser updateFirstName(RegisterUser login) throws RegisterUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisterUser updateLastName(RegisterUser login) throws RegisterUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisterUser> getAllRegisteredUser() throws RegisterUserException {
		// TODO Auto-generated method stub
		return null;
	}

}
