package com.capgemini.tlta.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.Technologylearningandtrackingappsprint2.TechnologyLearningAndTrackingAppSprint2Application;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.model.Role;
import com.capgemini.tlta.repository.RegisterUserRepository;

/**
 * The Class RegisterUserRestControllerIntegrationTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TechnologyLearningAndTrackingAppSprint2Application.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RegisterUserRestControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private RegisterUserRepository repository;

    /**
     * Reset db.
     */
    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    /**
     * When valid input then create register user.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws Exception the exception
     */
    @Test
    public void whenValidInput_thenCreateRegisterUser() throws IOException, Exception {
    	RegisterUser alex = new RegisterUser("Alex","Vele","alex@gmail.com","12345@fsq",Role.USER);
        mvc.perform(post("/api/users/")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(JsonUtil.toJson(alex)));

        List<RegisterUser> found = repository.findAll();
        assertThat(found).extracting(RegisterUser::getFirstName).containsOnly("Alex");
    }
    
    /**
     * Given register users when get register users then status 200.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenRegisterUsers_whenGetRegisterUsers_thenStatus200() throws Exception {
        createTestRegisterUser("Alex","Vele","alex@gmail.com","1as235@fsq",Role.USER);
        createTestRegisterUser("Bob","Sora","bob@gmail.com","1rea45@fsq",Role.USER);

        
        mvc.perform(get("/api/users/").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
          .andExpect(jsonPath("$[0].firstName", is("Alex")))
          .andExpect(jsonPath("$[1].firstName", is("Bob")));
        
    }
    
	/**
	 * Creates the test register user.
	 *
	 * @param name the name
	 */
	private void createTestRegisterUser(String name,String lastname,String email,String pass,Role role) {
		RegisterUser emp = new RegisterUser(name,lastname,email,pass,role);
		repository.saveAndFlush(emp);
	}
}
