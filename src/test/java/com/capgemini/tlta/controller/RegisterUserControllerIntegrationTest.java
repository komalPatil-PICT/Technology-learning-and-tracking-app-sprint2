package com.capgemini.tlta.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.Technologylearningandtrackingappsprint2.TechnologyLearningAndTrackingAppSprint2Application;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.sevice.RegisterUserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TechnologyLearningAndTrackingAppSprint2Application.class)
@AutoConfigureMockMvc
public class RegisterUserControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private RegisterUserService service;

	/**
	 * When post user then create user.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void whenPostUser_thenCreateUser() throws Exception {
		RegisterUser alex = new RegisterUser("Alex");
		given(service.addUser(Mockito.any())).willReturn(alex);

		mvc.perform(post("/api/users/")
				.contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("Alex")));

		verify(service, VerificationModeFactory.times(1)).addUser(Mockito.any());
		reset(service);
	}
	
	//TODO: 
//	@Test
//	public void whenGetUserById_thenReturnUserById() throws Exception {
//		RegisterUser alex = new RegisterUser("Alex");
//		given(service.getUserById(Mockito.anyInt())).willReturn(alex);
//		Integer id = alex.getId();
//		// http://localhost:8081/springfox/api/users/
//		mvc.perform(get("/api/users/"+id)
//				.contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex)))
//				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(id)));
//
//		verify(service, VerificationModeFactory.times(1)).getUserById(Mockito.anyInt());
//		reset(service);
//	}

	/**
	 * Given users when get users then return json array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {
		RegisterUser alex = new RegisterUser("alex");
		RegisterUser john = new RegisterUser("john");
		RegisterUser bob = new RegisterUser("bob");

		List<RegisterUser> allRegisterUsers = Arrays.asList(alex, john, bob);

		given(service.getAllRegisteredUser()).willReturn(allRegisterUsers);

		mvc.perform(get("/api/users/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].firstName", is(alex.getFirstName())))
				.andExpect(jsonPath("$[1].firstName", is(john.getFirstName())))
				.andExpect(jsonPath("$[2].firstName", is(bob.getFirstName())));
		verify(service, VerificationModeFactory.times(1)).getAllRegisteredUser();
		reset(service);
	}
}
