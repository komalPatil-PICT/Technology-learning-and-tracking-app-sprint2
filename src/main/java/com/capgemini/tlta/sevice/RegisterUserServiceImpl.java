package com.capgemini.tlta.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.exception.RegisterUserException;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.repository.RegisterUserRepository;
@Service( value = "registerUserService")
@Transactional
public class RegisterUserServiceImpl implements RegisterUserService {

	@Autowired
	RegisterUserRepository userRepository;
	
	@Override
	public RegisterUser addUser(RegisterUser user) throws RegisterUserException {
		
		try {
			RegisterUser user1 = new RegisterUser();
			user1 = userRepository.save(user);
			return user1;
		}catch(DataAccessException e) {
			throw new RegisterUserException(e.getMessage(),e);
		}catch(Exception e) {
			throw new RegisterUserException(e.getMessage(),e);
		}
	}

	@Override
	public RegisterUser getUserById(Integer id) throws RegisterUserException {
		
		try {
			Optional<RegisterUser> optional= 
					userRepository.findById(id);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException e) {
			throw new RegisterUserException(e.getMessage(),e);
		}catch(Exception e) {
			throw new RegisterUserException(e.getMessage(),e);
		}
	}

	@Override
	public RegisterUser getModeratorById(Integer id) throws RegisterUserException {
		
		return null;
	}

	@Override
	public Integer deleteUser(Integer id) throws RegisterUserException {

		try {
			userRepository.deleteById(id);
			return 1;
		}catch(DataAccessException e) {
			throw new RegisterUserException(e.getMessage(),e);
		}catch(Exception e) {
			throw new RegisterUserException(e.getMessage(),e);
		}
	}

	@Override
	public RegisterUser updatePassword(RegisterUser user,String firstName,String lastName) throws RegisterUserException {
		RegisterUser updateUser = null;
		try {
			if(user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
				updateUser = userRepository.save(user);
			}
			else{
				return null;
			}
			return updateUser;
		}catch(DataAccessException e) {
			throw new RegisterUserException(e.getMessage(),e);
		}catch(Exception e) {
			throw new RegisterUserException(e.getMessage(),e);
		}
		
	}

	@Override
	public RegisterUser updateFirstName(RegisterUser login) throws RegisterUserException {
		
		return null;
	}

	@Override
	public RegisterUser updateLastName(RegisterUser login) throws RegisterUserException {
		
		return null;
	}

	@Override
	public List<RegisterUser> getAllRegisteredUser() throws RegisterUserException {
		
		try {
			List<RegisterUser>usersList=
					userRepository.findAll();
			return usersList;
		}catch(DataAccessException e) {
			throw new RegisterUserException(e.getMessage(),e);
		}catch(Exception e) {
			throw new RegisterUserException(e.getMessage(),e);
		}
	}

}
