package com.capgemini.tlta.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.Technologylearningandtrackingappsprint2.TechnologyLearningAndTrackingAppSprint2Application;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.model.UserActivity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TechnologyLearningAndTrackingAppSprint2Application.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)

@DataJpaTest
@DirtiesContext

public class UserActivityRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserActivityRepository userActivityRepository;

	@Autowired
	private LearningActivityRepository learningRepository;
//	@Autowired
//	private AssessmentActivityRepository assessmentRepository;

	@BeforeEach
	public void resetDb() {
		learningRepository.deleteAll();
		userActivityRepository.deleteAll();
	}

	@Test
	public void whenFindById_thenReturnUser() {
		UserActivity p = new UserActivity("test");
		entityManager.persistAndFlush(p);

		UserActivity fromDb = userActivityRepository.findById(p.getUserActivityId()).orElse(null);
		assertThat(fromDb.getLearningActivity()).isEqualTo(p.getLearningActivity());
	}

	@Test
	public void whenInvalidId_thenReturnNull() {
		UserActivity fromDb = userActivityRepository.findById(-11).orElse(null);
		assertThat(fromDb).isNull();
	}

	@Test
	public void givenSetOfUsers_whenFindAll_thenReturnAllLearningActivity() {
		
		UserActivity java = new UserActivity("java");
		UserActivity jpa = new UserActivity("jpa");
		UserActivity cpp = new UserActivity("cpp");

		entityManager.persist(java);
		entityManager.persist(jpa);
		entityManager.persist(cpp);
		entityManager.flush();

		List<UserActivity> allUserActivity = userActivityRepository.findAll();

		assertThat(allUserActivity).hasSize(3).
		extracting(UserActivity::getLearningActivity).containsOnly(java.getLearningActivity(),jpa.getLearningActivity(), cpp.getLearningActivity());
	}


	/**
	 * Update assessment test.
	 */

    @Test
    public void deleteAssessmentActivity_test() {
    	
    	UserActivity useractivity = new UserActivity("React");
		entityManager.persistAndFlush(useractivity);
		
		// check if insertion is successful
		assertNotNull(entityManager.find(UserActivity.class, useractivity.getUserActivityId()));
    	
    	entityManager.remove(useractivity);
        
    	UserActivity deletedActivity = entityManager.find(UserActivity.class, useractivity.getUserActivityId());
        assertNull(deletedActivity);
    }
}



