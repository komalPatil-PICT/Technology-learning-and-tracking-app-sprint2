package com.capgemini.tlta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.LearningActivity;

/**
 * The Interface LearningActivityRepository.
 */
@Repository
public interface LearningActivityRepository extends JpaRepository<LearningActivity, Integer>{

}
