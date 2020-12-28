package com.capgemini.tlta.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.Technologylearningandtrackingappsprint2.TechnologyLearningAndTrackingAppSprint2Application;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.repository.AssessmentActivityRepository;
import com.capgemini.tlta.repository.LearningActivityRepository;

/**
 * The Class LearningActivityRestControllerIntegrationTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TechnologyLearningAndTrackingAppSprint2Application.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class LearningActivityRestControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private AssessmentActivityRepository repository;

	@Autowired
	private LearningActivityRepository learningRepository;

	/**
	 * Reset db.
	 */
	@AfterEach
	public void resetDb() {
		learningRepository.deleteAll();
		repository.deleteAll();
	}

	/**
	 * Given learning activities when get learning activities then status 200.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void givenLearningActivities_whenGetLearningActivities_thenStatus200() throws Exception {
		createTestLearningActivity("Java");
		createTestLearningActivity("Jpa");

		mvc.perform(get("/api/learningActivity/").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
				.andExpect(jsonPath("$[0].activityName", is("Java")))
				.andExpect(jsonPath("$[1].activityName", is("Jpa")));

	}

	/**
	 * Creates the test learning activity.
	 *
	 * @param name the name
	 */
	private void createTestLearningActivity(String name) {
		LearningActivity emp = new LearningActivity(name);
		learningRepository.saveAndFlush(emp);
	}
}
