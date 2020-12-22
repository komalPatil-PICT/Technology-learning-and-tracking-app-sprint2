package com.capgemini.tlta.repository;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.RegisterUser;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, Integer>{
	
//	public void addUser(RegisterUser login) throws PersistenceException;
//
//	public RegisterUser getUserById(Integer id) throws PersistenceException;
//
//	public RegisterUser getModeratorById(Integer id) throws PersistenceException;
//
//	public Integer deleteUser(Integer id) throws PersistenceException;
//
//	public RegisterUser updatePassword(RegisterUser login) throws PersistenceException;
//
//	public RegisterUser updatePassword(RegisterUser login, String email, String lastName) throws PersistenceException;
//
//	public RegisterUser updateFirstName(RegisterUser login) throws PersistenceException;
//
//	public RegisterUser updateLastName(RegisterUser login) throws PersistenceException;
//
//	public List<RegisterUser> getAllRegisteredUser() throws PersistenceException;
//
//

	}