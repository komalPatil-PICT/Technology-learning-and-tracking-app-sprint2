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
import com.capgemini.tlta.model.RegisterUser;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TechnologyLearningAndTrackingAppSprint2Application.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
@DirtiesContext
public class RegisterUserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RegisterUserRepository userRepository;

    @BeforeEach
    public void resetDb() {
        userRepository.deleteAll();
    }
    
    @Test
    public void whenFindById_thenReturnUser() {
    	RegisterUser user = new RegisterUser("Bhavana");
        entityManager.persistAndFlush(user);

        RegisterUser fromDb = userRepository.findById(user.getId()).orElse(null);
        assertThat(fromDb.getFirstName()).isEqualTo(user.getFirstName());
    }
    @Test
    public void whenInvalidId_thenReturnNull() {
    	RegisterUser fromDb = userRepository.findById(-11).orElse(null);
        assertThat(fromDb).isNull();
    }
    
    @Test
    public void givenSetOfUsers_whenFindAll_thenReturnAllUsers() {
    	RegisterUser alex = new RegisterUser("alex");
    	RegisterUser ron = new RegisterUser("ron");
    	RegisterUser bob = new RegisterUser("bob");

        entityManager.persist(alex);
        entityManager.persist(bob);
        entityManager.persist(ron);
        entityManager.flush();

        List<RegisterUser> allRegisterUsers = userRepository.findAll();

        assertThat(allRegisterUsers).hasSize(3).extracting(RegisterUser::getFirstName).
        containsOnly(alex.getFirstName(), ron.getFirstName(), bob.getFirstName());
    }
    
    @Test
    public void updateUserFirstName_test() {
    	RegisterUser user = new RegisterUser("bob");
        entityManager.persistAndFlush(user);
        user.setFirstName("alex");
       
        entityManager.persistAndFlush(user);  
        RegisterUser updatedUser = entityManager.find(RegisterUser.class, user.getId());
        
        assertThat(updatedUser.getFirstName()).isEqualTo(user.getFirstName());
    }
    
    @Test
    public void deleteUser_test() {
    	
    	// add a new RegisterUser
    	RegisterUser user = new RegisterUser("john");
    	
    	// save new RegisterUser to database repository
    	entityManager.persistAndFlush(user);
    	
    	// test if RegisterUser is inserted
    	assertNotNull(entityManager.find(RegisterUser.class, user.getId()));
    	
    	// delete the inserted RegisterUser
        entityManager.remove(user);
        
        // test if the RegisterUser is deleted
        RegisterUser deleted= entityManager.find(RegisterUser.class, user.getId());
        assertNull(deleted);
    }}
