package com.duke.nurseryschool;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseTestCase {

	/**
	 * Initializes fields annotated with Mockito annotations
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

}
