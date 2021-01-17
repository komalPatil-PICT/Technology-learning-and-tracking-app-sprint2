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

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.repository.AssessmentActivityRepository;
import com.capgemini.tlta.repository.LearningActivityRepository;
import com.capgemini.tlta.sevice.LearningActivityDO;
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
    	LearningActivityDO java = new LearningActivityDO("Java","http://java.com","intermediate",3d,new Date());
		LearningActivityDO jpa = new LearningActivityDO("Jpa","http://java.com","intermediate",3d,new Date());
		LearningActivityDO cpp = new LearningActivityDO("Cpp","http://java.com","intermediate",3d,new Date());
    	
    	LearningActivity java1 = new LearningActivity(java);
        java1.setId(11);

        LearningActivity cpp1 = new LearningActivity(cpp);
        LearningActivity jpa1 = new LearningActivity(jpa);

        List<LearningActivity> LearningActivity = Arrays.asList(java1, jpa1, cpp1);

        Mockito.when(learningActivityRepository.findById(java1.getId())).thenReturn(Optional.of(java1));
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
    	LearningActivityDO java = new LearningActivityDO("Java","http://java.com","intermediate",3d,new Date());
		LearningActivityDO jpa = new LearningActivityDO("Jpa","http://java.com","intermediate",3d,new Date());
		LearningActivityDO cpp = new LearningActivityDO("Cpp","http://java.com","intermediate",3d,new Date());
    	
    	LearningActivity java1 = new LearningActivity(java);
    	LearningActivity jpa1 = new LearningActivity(jpa);
    	LearningActivity cpp1 = new LearningActivity(cpp);

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
