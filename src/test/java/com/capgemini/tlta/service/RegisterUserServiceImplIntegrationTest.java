package com.capgemini.tlta.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
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

import com.capgemini.tlta.exception.RegisterUserException;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.repository.RegisterUserRepository;
import com.capgemini.tlta.sevice.RegisterUserService;
import com.capgemini.tlta.sevice.RegisterUserServiceImpl;

/**
 * The Class RegisterUserServiceImplIntegrationTest.
 */
@ExtendWith(SpringExtension.class)
public class RegisterUserServiceImplIntegrationTest {

	/**
	 * The Class registerUserServiceImplTestContextConfiguration.
	 */
	@TestConfiguration
    static class registerUserServiceImplTestContextConfiguration {
        
        /**
         * Register user service.
         *
         * @return the register user service
         */
        @Bean
        public RegisterUserService registerUserService() {
            return new RegisterUserServiceImpl();
        }
    }

    @Autowired
    private RegisterUserService userService;

    @MockBean
    private RegisterUserRepository userRepository;
    
    /**
     * Sets up the test data source.
     */
    @BeforeEach
    public void setUp() {
    	RegisterUser john = new RegisterUser("john");
        john.setId(11);

        RegisterUser bob = new RegisterUser("bob");
        RegisterUser alex = new RegisterUser("alex");

        List<RegisterUser> users = Arrays.asList(john, bob, alex);

        Mockito.when(userRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(userRepository.findById(-99)).thenReturn(Optional.empty());
    }
    
    
    /**
     * When valid id then user should be found.
     *
     * @throws RegisterUserException the register user exception
     */
    @Test
    public void whenValidId_thenUserShouldBeFound() throws RegisterUserException {
        RegisterUser fromDb = userService.getUserById(11);
        assertThat(fromDb.getFirstName()).isEqualTo("john");

        verifyFindByIdIsCalledOnce();
    }
    
    /**
     * When in valid id then user should not be found.
     *
     * @throws RegisterUserException the register user exception
     */
    @Test
    public void whenInValidId_thenUserShouldNotBeFound() throws RegisterUserException {
        RegisterUser fromDb = userService.getUserById(-99);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }
    
    /**
     * Given 3 users when get all then return 3 records.
     *
     * @throws RegisterUserException the register user exception
     */
    @Test
    public void given3Users_whenGetAll_thenReturn3Records() throws RegisterUserException {
        RegisterUser alex = new RegisterUser("alex");
        RegisterUser john = new RegisterUser("john");
        RegisterUser bob = new RegisterUser("bob");

        List<RegisterUser> allUsers = userService.getAllRegisteredUser();
        verifyFindAllUsersIsCalledOnce();
        assertThat(allUsers).hasSize(3).extracting(RegisterUser::getFirstName).contains(alex.getFirstName(), john.getFirstName(), bob.getFirstName());
    }
    
    /**
     * Verify find by id is called once.
     */
    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findById(Mockito.anyInt());
        Mockito.reset(userRepository);
    }
    
    
    /**
     * Verify find all users is called once.
     */
    private void verifyFindAllUsersIsCalledOnce() {
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(userRepository);
    }
}
