package com.example.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class CourseControllerTest extends AbstractControllerTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testGetCourses() throws Exception {
		String uri = "/api/courses";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}