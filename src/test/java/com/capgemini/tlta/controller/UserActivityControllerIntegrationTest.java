package com.capgemini.tlta.controller;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.Technologylearningandtrackingappsprint2.TechnologyLearningAndTrackingAppSprint2Application;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.model.UserActivity;
import com.capgemini.tlta.sevice.AssessmentActivityService;
import com.capgemini.tlta.sevice.LearningActivityDO;
import com.capgemini.tlta.sevice.UserActivityDO;
import com.capgemini.tlta.sevice.UserActivityService;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TechnologyLearningAndTrackingAppSprint2Application.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserActivityControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserActivityService userservice;

//	@Test
//	public void whenPostAssessment_thenCreateAssessment() throws Exception {
//		UserActivity jpa = new UserActivity("jpa");
//		
//		given(userservice.userRegisterToLearningActivity(Mockito.any())).willReturn(jpa);
//
//		mvc.perform(post("/api/userActivity/")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(JsonUtil.toJson(jpa)))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.status", is("jpa")));
//
//		verify(userservice, VerificationModeFactory.times(1)).userRegisterToLearningActivity(Mockito.any());
//		reset(userservice);
//	}

	@Test
	public void givenUserActivity_whenGetUserActivity_thenReturnJsonArray() throws Exception {
		UserActivity jpa = new UserActivity("jpa");
		UserActivity java = new UserActivity("java");
		UserActivity cpp = new UserActivity("cpp");

		List<UserActivity> allUserActivity = Arrays.asList(jpa, java, cpp);

		given(userservice.getAllUserActivities()).willReturn(allUserActivity);

		mvc.perform(get("/api/userActivity/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].learningActivity", is(jpa.getLearningActivity())))
				.andExpect(jsonPath("$[1].learningActivity", is(java.getLearningActivity())))
				.andExpect(jsonPath("$[2].learningActivity", is(cpp.getLearningActivity())));
		verify(userservice, VerificationModeFactory.times(1)).getAllUserActivities();
		reset(userservice);
	}

}