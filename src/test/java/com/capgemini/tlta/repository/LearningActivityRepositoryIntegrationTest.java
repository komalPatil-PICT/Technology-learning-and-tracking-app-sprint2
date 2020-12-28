package com.capgemini.tlta.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.Technologylearningandtrackingappsprint2.TechnologyLearningAndTrackingAppSprint2Application;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.sevice.LearningActivityDO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TechnologyLearningAndTrackingAppSprint2Application.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class LearningActivityRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private LearningActivityRepository learningActivityRepository;

	/**
	 * When find by id then return learning activity.
	 */
	@Test
	public void whenFindById_thenReturnLearningActivity() {
		LearningActivityDO java = new LearningActivityDO("Java","http://java.com","intermediate",3d,new Date());
		
		LearningActivity learningActivity = new LearningActivity(java);
		entityManager.persistAndFlush(learningActivity);

		LearningActivity fromDb = learningActivityRepository.findById(learningActivity.getId()).orElse(null);
		assertThat(fromDb.getActivityName()).isEqualTo(learningActivity.getActivityName());
	}

	/**
	 * When invalid id then return null.
	 */
	@Test
	public void whenInvalidId_thenReturnNull() {
		LearningActivity fromDb = learningActivityRepository.findById(-11).orElse(null);
		assertThat(fromDb).isNull();
	}

	/**
	 * Given set of learning activities when find all then return all learning
	 * activities.
	 */
	@Test
	public void givenSetOfLearningActivities_whenFindAll_thenReturnAllLearningActivities() {
		LearningActivityDO java = new LearningActivityDO("Java","http://java.com","intermediate",3d,new Date());
		LearningActivityDO jpa = new LearningActivityDO("Jpa","http://java.com","intermediate",3d,new Date());
		LearningActivityDO cpp = new LearningActivityDO("Cpp","http://java.com","intermediate",3d,new Date());
		
		LearningActivity java1 = new LearningActivity(java);
		LearningActivity jpa1 = new LearningActivity(jpa);
		LearningActivity cpp1 = new LearningActivity(cpp);

		entityManager.persist(java1);
		entityManager.persist(jpa1);
		entityManager.persist(cpp1);
		entityManager.flush();

		List<LearningActivity> allactivity = learningActivityRepository.findAll();

		assertThat(allactivity).hasSize(3).extracting(LearningActivity::getActivityName)
				.containsOnly(java.getActivityName(), jpa.getActivityName(), cpp.getActivityName());
	}

	/**
	 * Update learning activity test.
	 */
	@Test
	public void updateLearningActivity_test() {
		LearningActivity activity = new LearningActivity("test","http://java.com","intermediate",3d,new Date());
		entityManager.persistAndFlush(activity);
		activity.setActivityName("java");

		entityManager.persistAndFlush(activity);
		LearningActivity updatedActivity = entityManager.find(LearningActivity.class, activity.getId());

		assertThat(updatedActivity.getActivityName()).isEqualTo(activity.getActivityName());
	}

	/**
	 * Delete learning activity test.
	 */
	@Test
	public void deleteLearningActivity_test() {
		LearningActivity java = new LearningActivity("java","http://java.com","intermediate",3d,new Date());
		entityManager.persistAndFlush(java);
		assertNotNull(entityManager.find(LearningActivity.class, java.getId()));
		entityManager.remove(java);
		LearningActivity deleted = entityManager.find(LearningActivity.class, java.getId());
		assertNull(deleted);
	}
}