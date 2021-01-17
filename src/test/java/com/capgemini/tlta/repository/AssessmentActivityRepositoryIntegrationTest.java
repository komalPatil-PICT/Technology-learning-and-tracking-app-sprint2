package com.capgemini.tlta.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
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

/**
 * The Class AssessmentActivityRepositoryIntegrationTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TechnologyLearningAndTrackingAppSprint2Application.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class AssessmentActivityRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AssessmentActivityRepository assessmentActivityRepository;

	@Autowired
	private LearningActivityRepository learningRepository;

	/**
	 * When find by id then return user.
	 */
	@Test
	public void whenFindById_thenReturnUser() {
		Assessment p = new Assessment("test","MCQ",new Date(),4d);
		entityManager.persistAndFlush(p);

		Assessment fromDb = assessmentActivityRepository.findById(p.getId()).orElse(null);
		assertThat(fromDb.getAssessmentName()).isEqualTo(p.getAssessmentName());
	}

	/**
	 * When invalid id then return null.
	 */
	@Test
	public void whenInvalidId_thenReturnNull() {
		Assessment fromDb = assessmentActivityRepository.findById(-11).orElse(null);
		assertThat(fromDb).isNull();
	}

	/**
	 * Given set of users when find all then return all assessments.
	 */
	@Test
	public void givenSetOfUsers_whenFindAll_thenReturnAllAssessments() {
		
		Assessment java = new Assessment("java","MCQ",new Date(),4d);
		Assessment jpa = new Assessment("jpa","MCQ",new Date(),4d);
		Assessment cpp = new Assessment("cpp","MCQ",new Date(),4d);

		entityManager.persist(java);
		entityManager.persist(jpa);
		entityManager.persist(cpp);
		entityManager.flush();

		List<Assessment> allAssessments = assessmentActivityRepository.findAll();

		assertThat(allAssessments).hasSize(3).
		extracting(Assessment::getAssessmentName).containsOnly(java.getAssessmentName(),
				jpa.getAssessmentName(), cpp.getAssessmentName());
	}


	/**
	 * Update assessment test.
	 */
	@Test
	public void updateAssessment_test() {
		Assessment assessment = new Assessment("test","MCQ",new Date(),4d);
		entityManager.persistAndFlush(assessment);		
		
		assertNotNull(entityManager.find(Assessment.class, assessment.getId()));
		
		assessment.setAssessmentName("java");
		entityManager.persistAndFlush(assessment);
		
		Assessment updatedProduct = entityManager.find(Assessment.class, assessment.getId());
		assertThat(updatedProduct.getAssessmentName()).isEqualTo(assessment.getAssessmentName());
	}
	
    /**
     * Delete assessment activity test.
     */
    @Test
    public void deleteAssessmentActivity_test() {
    	
    	Assessment assessment = new Assessment("React","MCQ",new Date(),4d);
		entityManager.persistAndFlush(assessment);
		
		// check if insertion is successful
		assertNotNull(entityManager.find(Assessment.class, assessment.getId()));
    	
    	entityManager.remove(assessment);
        
    	Assessment deletedProduct = entityManager.find(Assessment.class, assessment.getId());
        assertNull(deletedProduct);
    }
}