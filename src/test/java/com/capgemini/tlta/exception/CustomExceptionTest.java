package com.capgemini.tlta.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.Technologylearningandtrackingappsprint2.TechnologyLearningAndTrackingAppSprint2Application;
import com.capgemini.tlta.model.LearningActivity;
import com.capgemini.tlta.repository.LearningActivityRepository;

/**
 * The Class CustomExceptionTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TechnologyLearningAndTrackingAppSprint2Application.class)
public class CustomExceptionTest {

	@Autowired
	LearningActivityRepository repository;

	/**
	 * When exception thrown then assertion succeeds.
	 */
	@Test
	public void whenExceptionThrown_thenAssertionSucceeds() {
		Exception exception = assertThrows(NumberFormatException.class, () -> {
			Integer.parseInt("1a");
		});

		String expectedMessage = "For input string";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	/**
	 * When derived exception thrown then assertion succeds.
	 */
	@Test
	public void whenDerivedExceptionThrown_thenAssertionSucceds() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			Integer.parseInt("1a");
		});

		String expectedMessage = "For input string";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	/**
	 * When invalid exception thrown then assertion succeeds.
	 */
	@Test
	void whenInvalidExceptionThrown_thenAssertionSucceeds() {
	 
	  Assertions.assertThrows(NullPointerException.class, () -> {
	    String name = null;
	    name.length();
	  });
	}
	
	/**
	 * When activity exception thrown then assertion succeeds.
	 */
	@Test
	void whenActivityExceptionThrown_thenAssertionSucceeds() {
	 
	  Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
	    LearningActivity activity = new LearningActivity();
	    activity.setId(null);
	    Integer id = activity.getId();
	    activity = repository.getOne(id);
	  });
	 
	}
}
