package com.sc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.sc.dto.CheckTask;
import com.sc.service.SCService;

@SpringBootTest
public class ScControllerTest {

	@Mock
	private SCService service;

	@InjectMocks
	private SCController controller;

	public static final String DUMMY_IDENTIFIER = "dummyIdentifier";

	@Test
	public void createTest() {
		when(service.create(Mockito.anyInt(), Mockito.anyInt())).thenReturn(DUMMY_IDENTIFIER);
		ResponseEntity<String> response = controller.create(1, 2);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(DUMMY_IDENTIFIER, response.getBody());
	}

	@Test
	public void createBadRequestTest() {
		when(service.create(Mockito.anyInt(), Mockito.anyInt())).thenReturn(DUMMY_IDENTIFIER);
		ResponseEntity<String> response = controller.create(1, 0);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Step should be positive and non zero.", response.getBody());
	}

	@Test
	public void checkTest() {
		when(service.check(Mockito.anyString())).thenReturn(new CheckTask(1, new Date(), 2));
		ResponseEntity<CheckTask> response = controller.check(DUMMY_IDENTIFIER);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void checkBadRequestTest() {
		when(service.check(Mockito.anyString())).thenReturn(null);
		ResponseEntity<CheckTask> response = controller.check(DUMMY_IDENTIFIER);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNull(response.getBody());
	}

	@Test
	public void checkAllTest() {
		List<CheckTask> checkTasks = new ArrayList<CheckTask>();
		checkTasks.add(new CheckTask(1, new Date(), 2));
		when(service.checkAll()).thenReturn(checkTasks);
		ResponseEntity<List<CheckTask>> response = controller.checkAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void renderTest() {
		when(service.render()).thenReturn(new ModelAndView());
		ModelAndView response = controller.render();
		assertNotNull(response);
	}

	@Test
	public void clearTest() {
		doNothing().when(service).clear(Mockito.anyString());
		ResponseEntity<Void> response = controller.clear(DUMMY_IDENTIFIER);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void pauseTest() {
		doNothing().when(service).pause(Mockito.anyString());
		ResponseEntity<Void> response = controller.pause(DUMMY_IDENTIFIER);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
