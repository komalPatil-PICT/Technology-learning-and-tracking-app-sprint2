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
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.repository.AssessmentActivityRepository;
import com.capgemini.tlta.repository.LearningActivityRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TechnologyLearningAndTrackingAppSprint2Application.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AssessmentRestControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private AssessmentActivityRepository repository;
	
	@Autowired
	private LearningActivityRepository learningRepository;
	
	@BeforeEach
	public void resetDb() {
		learningRepository.deleteAll();
		repository.deleteAll();
	}

	@Test
	public void whenValidInput_thenCreateAssessment() throws IOException, Exception {
		Assessment java = new Assessment("Java");
		mvc.perform(post("/api/assessments/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(java)));

		List<Assessment> found = repository.findAll();
		assertThat(found)
		.extracting(Assessment::getAssessmentName).containsOnly("Java");
	}

	@Test
	public void givenAssessments_whenGetAssessments_thenStatus200() throws Exception {
		createTestAssessment("Java");
		createTestAssessment("Jpa");

		mvc.perform(get("/api/assessments/").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
				.andExpect(jsonPath("$[0].assessmentName", is("Java")))
				.andExpect(jsonPath("$[1].assessmentName", is("Jpa")));

	}

	private void createTestAssessment(String name) {
		Assessment emp = new Assessment(name);
		repository.saveAndFlush(emp);
	}

}
