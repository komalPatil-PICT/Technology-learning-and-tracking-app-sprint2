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
import com.capgemini.tlta.model.RegisterUser;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TechnologyLearningAndTrackingAppSprint2Application.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
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

}
