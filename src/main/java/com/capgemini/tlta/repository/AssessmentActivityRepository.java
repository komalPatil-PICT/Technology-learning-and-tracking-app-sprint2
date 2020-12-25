package com.capgemini.tlta.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.Assessment;

/**
 * The Interface AssessmentActivityRepository.
 */
@Repository
public interface AssessmentActivityRepository extends JpaRepository<Assessment, Integer>{

     
}

