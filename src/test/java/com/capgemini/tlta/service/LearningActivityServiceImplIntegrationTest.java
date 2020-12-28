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

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.repository.AssessmentActivityRepository;
import com.capgemini.tlta.repository.LearningActivityRepository;
import com.capgemini.tlta.sevice.LearningActivityService;
import com.capgemini.tlta.sevice.LearningActivityServiceImpl;

/**
 * The Class LearningActivityServiceImplIntegrationTest.
 */
@ExtendWith(SpringExtension.class)
public class LearningActivityServiceImplIntegrationTest {
	
	/**
	 * The Class LearningActivityServiceImplTestContextConfiguration.
	 */
	@TestConfiguration
    static class LearningActivityServiceImplTestContextConfiguration {
        
        /**
         * Learning activity service.
         *
         * @return the learning activity service
         */
        @Bean
        public LearningActivityService learningActivityService() {
            return new LearningActivityServiceImpl();
        }
    }

    @Autowired
    private LearningActivityService learningActivityService;

    @MockBean
    private LearningActivityRepository learningActivityRepository;
    
    @MockBean
    private AssessmentActivityRepository assessmentRepository;
    
    /**
     * Sets up the test data source.
     */
    @BeforeEach
    public void setUp() {
    	LearningActivity java = new LearningActivity("Java");
        java.setId(11);

        LearningActivity cpp = new LearningActivity("cpp");
        LearningActivity jpa = new LearningActivity("jpa");

        List<LearningActivity> LearningActivity = Arrays.asList(java, jpa, cpp);

        Mockito.when(learningActivityRepository.findById(java.getId())).thenReturn(Optional.of(java));
        Mockito.when(learningActivityRepository.findAll()).thenReturn(LearningActivity);
        Mockito.when(learningActivityRepository.findById(-99)).thenReturn(Optional.empty());
    }
    
    
    /**
     * When valid id then learning activity should be found.
     *
     * @throws ActivityException the activity exception
     */
    @Test
    public void whenValidId_thenLearningActivityShouldBeFound() throws ActivityException {
        LearningActivity fromDb = learningActivityService.searchLearningActivityById(11);
        assertThat(fromDb.getActivityName()).isEqualTo("Java");

        verifyFindByIdIsCalledOnce();
    }
    
    
    /**
     * When invalid id then learning activity should not be found.
     *
     * @throws ActivityException the activity exception
     */
    @Test
    public void whenInvalidId_thenLearningActivityShouldNotBeFound() throws ActivityException {
        LearningActivity fromDb = learningActivityService.searchLearningActivityById(-99);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }
    
    /**
     * Given 3 learning activity when get all then return 3 records.
     *
     * @throws ActivityException the activity exception
     */
    @Test
    public void given3LearningActivity_whenGetAll_thenReturn3Records() throws ActivityException {
    	LearningActivity java = new LearningActivity("Java");
    	LearningActivity jpa = new LearningActivity("jpa");
    	LearningActivity cpp = new LearningActivity("cpp");

        List<LearningActivity> allLearningActivity = learningActivityService.getAllLearningActivity();
        verifyFindAllLearningActivitiesIsCalledOnce();
        assertThat(allLearningActivity).hasSize(3).
        extracting(LearningActivity::getActivityName).
        contains(java.getActivityName(), 
        		jpa.getActivityName(), 
        		cpp.getActivityName());
    }
    
    /**
     * Verify find by id is called once.
     */
    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(learningActivityRepository, VerificationModeFactory.times(1)).
        findById(Mockito.anyInt());
        Mockito.reset(learningActivityRepository);
    }
    
    
    /**
     * Verify find all learning activities is called once.
     */
    private void verifyFindAllLearningActivitiesIsCalledOnce() {
        Mockito.verify(learningActivityRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(learningActivityRepository);
    }
}
