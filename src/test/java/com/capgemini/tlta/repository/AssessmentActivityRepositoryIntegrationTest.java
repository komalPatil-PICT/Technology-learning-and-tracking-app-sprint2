package com.capgemini.tlta.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.model.RegisterUser;

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
	
	@BeforeEach
	public void resetDb() {
		learningRepository.deleteAll();
		assessmentActivityRepository.deleteAll();
	}

	@Test
	public void whenFindById_thenReturnUser() {
		Assessment p = new Assessment("test");
		entityManager.persistAndFlush(p);

		Assessment fromDb = assessmentActivityRepository.findById(p.getId()).orElse(null);
		assertThat(fromDb.getAssessmentName()).isEqualTo(p.getAssessmentName());
	}

	@Test
	public void whenInvalidId_thenReturnNull() {
		Assessment fromDb = assessmentActivityRepository.findById(-11).orElse(null);
		assertThat(fromDb).isNull();
	}

	@Test
	public void givenSetOfUsers_whenFindAll_thenReturnAllAssessments() {
		
		Assessment java = new Assessment("java");
		Assessment jpa = new Assessment("jpa");
		Assessment cpp = new Assessment("cpp");

		entityManager.persist(java);
		entityManager.persist(jpa);
		entityManager.persist(cpp);
		entityManager.flush();

		List<Assessment> allAssessments = assessmentActivityRepository.findAll();

		assertThat(allAssessments).hasSize(3).
		extracting(Assessment::getAssessmentName).containsOnly(java.getAssessmentName(),
				jpa.getAssessmentName(), cpp.getAssessmentName());
	}

//
//	@Test
//	public void testUpdateAssessment() {
//		Assessment p = new Assessment("test");
//		entityManager.persistAndFlush(p);
//		p.setAssessmentName("java");
//
//		assessmentActivityRepository.save(p);
//
//		Assessment updatedProduct = assessmentActivityRepository.findById(p.getId()).orElse(null);
//
//		assertThat(updatedProduct.getAssessmentName()).isEqualTo(p.getAssessmentName());
//	}
}