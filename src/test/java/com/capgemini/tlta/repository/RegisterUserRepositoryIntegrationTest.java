package com.capgemini.tlta.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
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
import com.capgemini.tlta.model.Role;


/**
 * The Class RegisterUserRepositoryIntegrationTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TechnologyLearningAndTrackingAppSprint2Application.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
public class RegisterUserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RegisterUserRepository userRepository;

	/**
	 * When find by id then return user.
	 */
	@Test
	public void whenFindById_thenReturnUser() {
		RegisterUser user = new RegisterUser("Bhavana","Vele","alex@gmail.com","12@#sdsd345",Role.USER);
		entityManager.persistAndFlush(user);

		RegisterUser fromDb = userRepository.findById(user.getId()).orElse(null);
		assertThat(fromDb.getFirstName()).isEqualTo(user.getFirstName());
	}
    
    /**
     * When invalid id then return null.
     */
    @Test
    public void whenInvalidId_thenReturnNull() {
    	RegisterUser fromDb = userRepository.findById(-11).orElse(null);
        assertThat(fromDb).isNull();
    }
    
    /**
     * Given set of users when find all then return all users.
     */
    @Test
    public void givenSetOfUsers_whenFindAll_thenReturnAllUsers() {
    	RegisterUser alex = new RegisterUser("alex","Vele","alex@gmail.com","12#$%d",Role.USER);
    	RegisterUser ron = new RegisterUser("ron","Vele","alex@gmail.com","12#$%d",Role.USER);
    	RegisterUser bob = new RegisterUser("bob","Vele","alex@gmail.com","12&^as",Role.USER);

    	userRepository.save(alex);
    	userRepository.save(ron);
    	userRepository.save(bob);
//        entityManager.persist(alex);
//        entityManager.persist(bob);
//        entityManager.persist(ron);
//        entityManager.flush();

        List<RegisterUser> allRegisterUsers = userRepository.findAll();

        assertThat(allRegisterUsers).hasSize(3).extracting(RegisterUser::getFirstName).
        containsOnly(alex.getFirstName(), ron.getFirstName(), bob.getFirstName());
    }
    
    /**
     * Update user first name test.
     */
    @Test
    public void updateUserFirstName_test() {
    	RegisterUser user = new RegisterUser("bob","Vele","alex@gmail.com","123asas#$#45",Role.USER);
        entityManager.persistAndFlush(user);
        user.setFirstName("alex");
       
        entityManager.persistAndFlush(user);  
        RegisterUser updatedUser = entityManager.find(RegisterUser.class, user.getId());
        
        assertThat(updatedUser.getFirstName()).isEqualTo(user.getFirstName());
    }
    
    /**
     * Delete user test.
     */
    @Test
    public void deleteUser_test() {
    	RegisterUser user = new RegisterUser("john","Vele","alex@gmail.com","123AS#$#45",Role.USER);
    	entityManager.persistAndFlush(user);
    	
    	assertNotNull(entityManager.find(RegisterUser.class, user.getId()));
    	
        entityManager.remove(user);
        
        RegisterUser deleted= entityManager.find(RegisterUser.class, user.getId());
        assertNull(deleted);
    }
}
