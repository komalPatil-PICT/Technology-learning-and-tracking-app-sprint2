package com.capgemini.tlta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.RegisterUser;

/**
 * The Interface LoginRepository.
 */
@Repository
public interface LoginRepository extends JpaRepository<RegisterUser, Integer> {
	
	@Query("select rs from RegisterUser rs where rs.emailId=?1")
	Optional<RegisterUser> findByEmailId(String email);
	
}