package com.capgemini.tlta.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.repository.AssessmentActivityRepository;
import com.capgemini.tlta.sevice.AssessmentActivityService;
import com.capgemini.tlta.sevice.AssessmentActivityServiceImpl;

/**
 * The Class AssessmentServiceImplIntegrationTest.
 */
@ExtendWith(SpringExtension.class)
public class AssessmentServiceImplIntegrationTest {

	/**
	 * The Class assessmentServiceImplTestContextConfiguration.
	 */
	@TestConfiguration
	static class assessmentServiceImplTestContextConfiguration {

		/**
		 * Assessment service.
		 *
		 * @return the assessment activity service
		 */
		@Bean
		public AssessmentActivityService assessmentService() {
			return new AssessmentActivityServiceImpl();
		}
	}

	@Autowired
	private AssessmentActivityService assessmentService;

	@MockBean
	private AssessmentActivityRepository assessmentRepository;

	/**
	 * Sets up the test data source.
	 */
	@BeforeEach
	public void setUp() {
		Assessment java = new Assessment("Java","MCQ",new Date(),4d);
		java.setId(11);

		Assessment cpp = new Assessment("cpp","MCQ",new Date(),4d);
		Assessment jpa = new Assessment("jpa","MCQ",new Date(),4d);

		List<Assessment> assessments = Arrays.asList(java, jpa, cpp);

		Mockito.when(assessmentRepository.findById(java.getId())).thenReturn(Optional.of(java));
		Mockito.when(assessmentRepository.findAll()).thenReturn(assessments);
		Mockito.when(assessmentRepository.findById(-99)).thenReturn(Optional.empty());
	}

	/**
	 * When valid id then assessment should be found.
	 *
	 * @throws AssesmentException the assesment exception
	 */
	@Test
	public void whenValidId_thenAssessmentShouldBeFound() throws AssesmentException {
		Assessment fromDb = assessmentService.searchAssessmentActivityById(11);
		assertThat(fromDb.getAssessmentName()).isEqualTo("Java");

		verifyFindByIdIsCalledOnce();
	}

	/**
	 * When in valid id then assessment should not be found.
	 *
	 * @throws AssesmentException the assesment exception
	 */
	@Test
	public void whenInValidId_thenAssessmentShouldNotBeFound() throws AssesmentException {
		Assessment fromDb = assessmentService.searchAssessmentActivityById(-99);
		verifyFindByIdIsCalledOnce();
		assertThat(fromDb).isNull();
	}

	/**
	 * Given 3 assessment when get all then return 3 records.
	 *
	 * @throws AssesmentException the assesment exception
	 */
	@Test
	public void given3Assessment_whenGetAll_thenReturn3Records() throws AssesmentException {
		Assessment java = new Assessment("Java","MCQ",new Date(),4d);
		Assessment jpa = new Assessment("jpa","MCQ",new Date(),4d);
		Assessment cpp = new Assessment("cpp","MCQ",new Date(),4d);

		List<Assessment> allAssessments = assessmentService.getAllAssessmentActivity();
		verifyFindAllAssessmentsIsCalledOnce();
		assertThat(allAssessments).hasSize(3).extracting(Assessment::getAssessmentName)
				.contains(java.getAssessmentName(), jpa.getAssessmentName(), cpp.getAssessmentName());
	}

	/**
	 * Verify find by id is called once.
	 */
	private void verifyFindByIdIsCalledOnce() {
		Mockito.verify(assessmentRepository, VerificationModeFactory.times(1)).findById(Mockito.anyInt());
		Mockito.reset(assessmentRepository);
	}

	/**
	 * Verify find all assessments is called once.
	 */
	private void verifyFindAllAssessmentsIsCalledOnce() {
		Mockito.verify(assessmentRepository, VerificationModeFactory.times(1)).findAll();
		Mockito.reset(assessmentRepository);
	}

}
