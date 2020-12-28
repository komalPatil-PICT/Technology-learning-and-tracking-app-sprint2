package com.capgemini.tlta.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.Technologylearningandtrackingappsprint2.TechnologyLearningAndTrackingAppSprint2Application;

import com.capgemini.tlta.model.UserActivity;
import com.capgemini.tlta.repository.AssessmentActivityRepository;

import com.capgemini.tlta.repository.UserActivityRepository;

/**
 * The Class UserActivityRestControllerIntegrationTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TechnologyLearningAndTrackingAppSprint2Application.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserActivityRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private UserActivityRepository userRepository;
	@Autowired
	private AssessmentActivityRepository repository;

	/**
	 * Reset DB.
	 */
	@BeforeEach
	void resetDB() {
		userRepository.deleteAll();
		repository.deleteAll();
	}

	/**
	 * Give status then status 200.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void giveStatus_thenStatus200() throws Exception {
		createTestStatus("Java");
		createTestStatus("Jpa");
		mvc.perform(get("/api/userActivity/").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
				.andExpect(jsonPath("$[0].status", is("Java"))).andExpect(jsonPath("$[1].status", is("Jpa")));

	}

	/**
	 * Creates the test status.
	 *
	 * @param name the name
	 */
	private void createTestStatus(String name) {
		UserActivity emp = new UserActivity(name);
		userRepository.saveAndFlush(emp);
	}
}