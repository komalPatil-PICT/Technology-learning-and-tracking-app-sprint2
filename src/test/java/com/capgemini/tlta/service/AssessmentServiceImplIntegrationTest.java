package com.capgemini.tlta.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
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

@ExtendWith(SpringExtension.class)
public class AssessmentServiceImplIntegrationTest {

	@TestConfiguration
    static class assessmentServiceImplTestContextConfiguration {
        @Bean
        public AssessmentActivityService assessmentService() {
            return new AssessmentActivityServiceImpl();
        }
    }

    @Autowired
    private AssessmentActivityService assessmentService;

    @MockBean
    private AssessmentActivityRepository assessmentRepository;
    
    @BeforeEach
    public void setUp() {
    	Assessment java = new Assessment("Java");
        java.setId(11);

        Assessment cpp = new Assessment("cpp");
        Assessment jpa = new Assessment("jpa");

        List<Assessment> assessments = Arrays.asList(java, jpa, cpp);

        Mockito.when(assessmentRepository.findById(java.getId())).thenReturn(Optional.of(java));
        Mockito.when(assessmentRepository.findAll()).thenReturn(assessments);
        Mockito.when(assessmentRepository.findById(-99)).thenReturn(Optional.empty());
    }
    
    @Test
    public void whenValidId_thenAssessmentShouldBeFound() throws AssesmentException {
        Assessment fromDb = assessmentService.searchAssessmentActivityById(11);
        assertThat(fromDb.getAssessmentName()).isEqualTo("Java");

        verifyFindByIdIsCalledOnce();
    }
    
    @Test
    public void whenInValidId_thenAssessmentShouldNotBeFound() throws AssesmentException {
        Assessment fromDb = assessmentService.searchAssessmentActivityById(-99);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }
    
    @Test
    public void given3Assessment_whenGetAll_thenReturn3Records() throws AssesmentException {
    	Assessment java = new Assessment("Java");
    	Assessment jpa = new Assessment("jpa");
    	Assessment cpp = new Assessment("cpp");

        List<Assessment> allAssessments = assessmentService.getAllAssessmentActivity();
        verifyFindAllAssessmentsIsCalledOnce();
        assertThat(allAssessments).hasSize(3).
        extracting(Assessment::getAssessmentName).
        contains(java.getAssessmentName(), 
        		jpa.getAssessmentName(), 
        		cpp.getAssessmentName());
    }
    
    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(assessmentRepository, VerificationModeFactory.times(1)).
        findById(Mockito.anyInt());
        Mockito.reset(assessmentRepository);
    }
    private void verifyFindAllAssessmentsIsCalledOnce() {
        Mockito.verify(assessmentRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(assessmentRepository);
    }

}
