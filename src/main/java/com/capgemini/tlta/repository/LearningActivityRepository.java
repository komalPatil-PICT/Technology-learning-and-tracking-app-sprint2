package com.capgemini.tlta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.LearningActivity;

@Repository
public interface LearningActivityRepository extends JpaRepository<LearningActivity, Integer>{

	@Query("select l from LearningActivity l")
	List<LearningActivity> getAllLearningActivities();
}
