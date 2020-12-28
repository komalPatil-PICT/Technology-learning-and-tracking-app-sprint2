package com.capgemini.tlta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.UserActivity;

/**
 * The Interface UserActivityRepository.
 */
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {

}
