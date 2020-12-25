package com.capgemini.tlta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.RegisterUser;

/**
 * The Interface RegisterUserRepository.
 */
@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, Integer> {

}