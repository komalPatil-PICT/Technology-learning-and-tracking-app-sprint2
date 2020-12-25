package com.capgemini.tlta.repository;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TechnologyLearningAndTrackingAppSprint2Application.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class LearningActivityRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private LearningActivityRepository learningActivityRepository;
	@BeforeEach
	public void resetDb() {
		learningActivityRepository.deleteAll();
	}
	
	/**
	 * When find by id then return learning activity.
	 */
	@Test
	public void whenFindById_thenReturnLearningActivity() {
		LearningActivity learningActivity = new LearningActivity("java");
		entityManager.persistAndFlush(learningActivity);

		LearningActivity fromDb = learningActivityRepository.findById(learningActivity.getId())
				.orElse(null);
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
	 * Given set of learning activities when find all then return all learning activities.
	 */
	@Test
	public void givenSetOfLearningActivities_whenFindAll_thenReturnAllLearningActivities() {
		LearningActivity java = new LearningActivity("java");
		LearningActivity jpa = new LearningActivity("jpa");
		LearningActivity cpp = new LearningActivity("cpp");

		entityManager.persist(java);
		entityManager.persist(jpa);
		entityManager.persist(cpp);
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
    	LearningActivity activity = new LearningActivity("test");
        entityManager.persistAndFlush(activity);
        activity.setActivityName("java");
       
        learningActivityRepository.save(activity);  
        LearningActivity updatedProduct = learningActivityRepository
        		.findById(activity.getId())
        		.orElse(null);
        assertThat(updatedProduct.getActivityName()).isEqualTo(activity.getActivityName());
    }
}